package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.client.UserClient;
import com.lmm.dto.AddShippingAddressDTO;
import com.lmm.entity.ShippingAddress;
import com.lmm.entity.Shop;
import com.lmm.entity.UserInfo;
import com.lmm.exception.QianBianException;
import com.lmm.mapper.ShippingAddressMapper;
import com.lmm.service.ShippingAddressService;
import com.lmm.service.ShopService;
import com.lmm.vo.ShippingAddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Service
public class ShippingAddressServiceImpl extends ServiceImpl<ShippingAddressMapper, ShippingAddress> implements ShippingAddressService {
    @Autowired
    private UserClient userClient;

    @Autowired
    private ShopService shopService;

    private void checkLoginUserWhetherHasQueryShop(Long userId, Long shopId) {
        UserInfo userInfo = userClient.findUserById(userId);
        if (userInfo.getShopId().equals(shopId)) {
            throw new QianBianException("您不是店主，请不要操作此店铺的优惠券");
        }
    }

    @Override
    public Boolean addShippingAddress(AddShippingAddressDTO addShippingAddressDTO, Long userId) {
        checkLoginUserWhetherHasQueryShop(userId, addShippingAddressDTO.getShopId());
        ShippingAddress shippingAddress = BeanUtil.copyProperties(addShippingAddressDTO, ShippingAddress.class);
        Integer priority = lambdaQuery().eq(ShippingAddress::getShopId, addShippingAddressDTO.getShopId()).count();
        shippingAddress.setPriority(priority);
        boolean success = save(shippingAddress);
        if (success && priority == 0) {
            return shopService
                    .lambdaUpdate()
                    .eq(Shop::getId, addShippingAddressDTO.getShopId())
                    .set(Shop::getDefaultAddressId, shippingAddress.getId())
                    .update();
        }
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteShippingAddress(Long shopId, Long shippingAddressId, Long userId) {
        checkLoginUserWhetherHasQueryShop(userId, shopId);
        int priority = lambdaQuery()
                .eq(ShippingAddress::getId, shippingAddressId)
                .select(ShippingAddress::getPriority)
                .one().getPriority();
        // 更新删除的收货地址之后的所有地址优先级
        List<ShippingAddress> needUpdateShippingAddress = lambdaQuery()
                .eq(ShippingAddress::getShopId, shopId)
                .select(ShippingAddress::getPriority, ShippingAddress::getId)
                .gt(ShippingAddress::getPriority, priority)
                .list();
        // 维护优先级
        if (needUpdateShippingAddress != null && !needUpdateShippingAddress.isEmpty()) {
            updateBatchById(needUpdateShippingAddress
                    .stream().map(u -> {
                        u.setPriority(u.getPriority() - 1);
                        return u;
                    }).collect(Collectors.toList()));
        }
        return lambdaUpdate()
                .eq(ShippingAddress::getId, shippingAddressId)
                .remove();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateDefaultShippingAddress(Long shopId, Long shippingAddressId, Integer priority, Long userId) {
        checkLoginUserWhetherHasQueryShop(userId, shopId);
        // 更新删除的收货地址之后的所有地址优先级
        List<ShippingAddress> needUpdateShippingAddress = lambdaQuery()
                .eq(ShippingAddress::getShopId, shopId)
                .select(ShippingAddress::getPriority, ShippingAddress::getId)
                .lt(ShippingAddress::getPriority, priority)
                .list();
        // 维护优先级
        if (needUpdateShippingAddress != null && !needUpdateShippingAddress.isEmpty()) {
            updateBatchById(needUpdateShippingAddress
                    .stream().map(u -> {
                        u.setPriority(u.getPriority() + 1);
                        return u;
                    }).collect(Collectors.toList()));
        }
        shopService.lambdaUpdate()
                .eq(Shop::getId, shopId)
                .set(Shop::getDefaultAddressId, shippingAddressId)
                .update();
        return lambdaUpdate()
                .eq(ShippingAddress::getId, shippingAddressId)
                // 0是默认地址
                .set(ShippingAddress::getPriority, 0)
                .update();
    }

    @Override
    public ShippingAddressVO defaultShippingAddress(Long shopId) {
        Shop shop = shopService.lambdaQuery().eq(Shop::getId, shopId).one();
        if (shop == null) {
            throw new QianBianException("店铺不存在");
        }
        return BeanUtil.copyProperties(
                lambdaQuery()
                        .eq(ShippingAddress::getId, shop.getDefaultAddressId())
                        .select(ShippingAddress::getProvince, ShippingAddress::getCounty, ShippingAddress::getCity)
                        .one(),
                ShippingAddressVO.class
        );
    }

    @Override
    public List<ShippingAddress> allShippingAddress(Long shopId, Long userId) {
        checkLoginUserWhetherHasQueryShop(userId, shopId);
        return lambdaQuery()
                .eq(ShippingAddress::getShopId, shopId)
                .orderByAsc(ShippingAddress::getPriority)
                .list();
    }
}