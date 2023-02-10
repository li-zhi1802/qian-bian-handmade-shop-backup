package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.client.ShopClient;
import com.lmm.dto.PageResult;
import com.lmm.dto.UserVoucherFilter;
import com.lmm.entity.Shop;
import com.lmm.entity.UserVoucher;
import com.lmm.entity.Voucher;
import com.lmm.mapper.UserVoucherMapper;
import com.lmm.service.UserVoucherService;
import com.lmm.service.VoucherService;
import com.lmm.vo.ShopVO;
import com.lmm.vo.UserVoucherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.lmm.constant.SystemConstant.USER_VOUCHER_PAGE_SIZE;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Service
public class UserVoucherServiceImpl extends ServiceImpl<UserVoucherMapper, UserVoucher> implements UserVoucherService {

    @Autowired
    private VoucherService voucherService;
    @Autowired
    private ShopClient shopClient;

    @Override
    public PageResult<UserVoucherVO> listUserVoucherByPage(Long pageNum, UserVoucherFilter filter, Long userId) {
        Page<UserVoucher> pageQuery = page(
                new Page<>(pageNum, USER_VOUCHER_PAGE_SIZE),
                new LambdaQueryWrapper<UserVoucher>()
                        .eq(UserVoucher::getUserId, userId)
                        .eq(UserVoucher::getState, filter.getUserVoucherState())
                        // 指定商铺。如果传了的话
                        .eq(filter.getShopId() != null, UserVoucher::getShopId, filter.getShopId())
                        .orderByDesc(UserVoucher::getGetTime)
        );
        List<Long> voucherIds = new LinkedList<>();
        // 得到要查询的所有的优惠券的id
        pageQuery.getRecords().forEach(r -> voucherIds.add(r.getVoucherId()));
        // 组装信息
        List<UserVoucherVO> voucherVOs = voucherService.lambdaQuery().in(Voucher::getId, voucherIds)
                // 按照指定顺序返回
                .last("ORDER BY FIELD(id," + CharSequenceUtil.join(",", voucherIds) + ")")
                .list().stream().map(v -> {
                    UserVoucherVO userVoucherVO = BeanUtil.copyProperties(v, UserVoucherVO.class);
                    Shop shopById = shopClient.getShopById(v.getShopId());
                    userVoucherVO.setShopVO(BeanUtil.copyProperties(shopById, ShopVO.class));
                    return userVoucherVO;
                }).collect(Collectors.toList());
        return PageResult.success(
                voucherVOs,
                pageNum,
                pageQuery.getTotal()
        );
    }

    @Override
    public Boolean deleteUserVoucher(Long voucherId, Long userId) {
        return lambdaUpdate()
                .eq(UserVoucher::getVoucherId, voucherId)
                .eq(UserVoucher::getUserId, userId).remove();
    }
}
