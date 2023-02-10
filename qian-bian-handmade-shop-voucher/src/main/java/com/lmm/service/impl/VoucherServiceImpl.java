package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.client.UserClient;
import com.lmm.constant.UserVoucherState;
import com.lmm.constant.VoucherState;
import com.lmm.dto.AddVoucherDTO;
import com.lmm.dto.PageResult;
import com.lmm.dto.UpdateVoucherDTO;
import com.lmm.entity.UserInfo;
import com.lmm.entity.UserVoucher;
import com.lmm.entity.Voucher;
import com.lmm.exception.QianBianException;
import com.lmm.mapper.VoucherMapper;
import com.lmm.service.UserVoucherService;
import com.lmm.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.lmm.constant.SystemConstant.VOUCHER_PAGE_SIZE;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Service
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, Voucher> implements VoucherService {

    @Autowired
    private UserVoucherService userVoucherService;
    @Autowired
    private UserClient userClient;

    private void checkLoginUserWhetherHasQueryShop(Long userId, Long shopId) {
        UserInfo userInfo = userClient.findUserById(userId);
        if (userInfo.getShopId().equals(shopId)) {
            throw new QianBianException("您不是店主，请不要操作此店铺的优惠券");
        }
    }

    @Override
    public Boolean addVoucher(AddVoucherDTO addVoucherDTO, Long userId) {
        checkLoginUserWhetherHasQueryShop(userId, addVoucherDTO.getShopId());

        Voucher voucher = BeanUtil.copyProperties(addVoucherDTO, Voucher.class);
        voucher.setCreatedTime(LocalDateTime.now());
        voucher.setUpdatedTime(LocalDateTime.now());
        voucher.setStock(addVoucherDTO.getQuota());
        voucher.setState(VoucherState.NOT_YET_ISSUED.getCode());
        voucher.setTitle("满" + voucher.getMinMoney() + "减" + voucher.getDecreaseMoney() + "券");
        return save(voucher);
    }

    @Override
    public Boolean distributionVoucher(Long voucherId, Long userId) {
        Long shopId = lambdaQuery().eq(Voucher::getId, voucherId).select(Voucher::getShopId).one().getShopId();
        // 检查是否是店主
        checkLoginUserWhetherHasQueryShop(userId, shopId);
        return lambdaUpdate().eq(Voucher::getId, voucherId).set(Voucher::getState, VoucherState.ISSUED.getCode()).update();
    }

    @Override
    public Boolean updateVoucher(UpdateVoucherDTO updateVoucherDTO, Long userId) {
        Voucher voucher = lambdaQuery()
                .eq(Voucher::getId, updateVoucherDTO.getVoucherId())
                .select(Voucher::getState, Voucher::getShopId)
                .one();
        // 检查是否是店主在操作
        checkLoginUserWhetherHasQueryShop(userId, voucher.getShopId());

        if (!VoucherState.NOT_YET_ISSUED.getCode().equals(voucher.getState())) {
            throw new QianBianException("只能修改未发布的优惠券信息");
        }
        Voucher update = BeanUtil.copyProperties(updateVoucherDTO, Voucher.class);
        update.setUpdatedTime(LocalDateTime.now());
        update.setTitle("满" + update.getMinMoney() + "减" + update.getDecreaseMoney() + "券");
        update.setStock(update.getQuota());
        // 更新数据库
        return saveOrUpdate(update);
    }

    @Override
    public PageResult<Voucher> pageQueryVouchersByShopId(Long shopId, Long pageNum) {
        return PageResult.success(
                page(
                        new Page<>(pageNum, VOUCHER_PAGE_SIZE),
                        new LambdaQueryWrapper<Voucher>()
                                .eq(Voucher::getShopId, shopId)
                )
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean receiveVoucher(Long voucherId, Long userId) {
        Voucher voucher = lambdaQuery().eq(Voucher::getId, voucherId).one();
        UserInfo userInfo = userClient.findUserById(userId);
        if (userInfo.getShopId() != null && userInfo.getShopId().equals(voucher.getShopId())) {
            throw new QianBianException("请店主不要领取自己店里的优惠券");
        }
        if (voucher == null) {
            throw new QianBianException("没有这张优惠券");
        }
        if (voucher.getStock() <= 0) {
            throw new QianBianException("没有库存了");
        }
        int count = userVoucherService.lambdaQuery().eq(UserVoucher::getVoucherId, voucherId).eq(UserVoucher::getUserId, userId).count();
        if (count >= voucher.getLimit()) {
            throw new QianBianException("每人只能领取" + voucher.getLimit() + "张优惠券");
        }
        // 为用户增加优惠券
        // 更新优惠券状态
        return userVoucherService.save(new UserVoucher(userId, voucher.getShopId(), voucherId, LocalDateTime.now(), UserVoucherState.RECEIVED.getCode())) &&
                lambdaUpdate()
                        .eq(Voucher::getId, voucherId)
                        // 拿的数量加一
                        .set(Voucher::getTakeAmount, voucher.getTakeAmount() + 1)
                        // 库存数量减一
                        .set(Voucher::getStock, voucher.getStock() - 1)
                        .update();
    }
}
