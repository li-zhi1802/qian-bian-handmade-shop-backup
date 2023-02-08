package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.dto.DeliveryAddressFormDTO;
import com.lmm.dto.RestResult;
import com.lmm.entity.DeliveryAddress;
import com.lmm.entity.UserInfo;
import com.lmm.exception.QianBianException;
import com.lmm.mapper.DeliveryAddressMapper;
import com.lmm.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryAddressServiceImpl extends ServiceImpl<DeliveryAddressMapper, DeliveryAddress> implements DeliveryAddressService {
    @Autowired
    private UserInfoServiceImpl userInfoService;
    /**
     * 当前类的代理对象
     */
    @Autowired
    private DeliveryAddressService proxy;

    @Override
    public RestResult getDefaultDeliveryAddress(Long userId) {
        Long defaultAddressId = userInfoService.lambdaQuery().select(UserInfo::getDefaultAddressId).eq(UserInfo::getId, userId).one().getDefaultAddressId();
        if (defaultAddressId == null) {
            throw new QianBianException("暂无默认收货地址!");
        }
        return RestResult.success(getById(defaultAddressId));
    }

    @Override
    public RestResult listAllDeliveryAddresses(Long userId) {
        return RestResult.success(
                lambdaQuery()
                        .eq(DeliveryAddress::getUserId, userId)
                        .orderByAsc(DeliveryAddress::getPriority)
                        .list()
        );
    }

    @Override
    public RestResult saveDeliveryAddress(DeliveryAddressFormDTO deliveryAddressFormDTO, Long userId) {
        DeliveryAddress deliveryAddress = BeanUtil.copyProperties(deliveryAddressFormDTO, DeliveryAddress.class);
        deliveryAddress.setUserId(userId);
        List<DeliveryAddress> ownAddresses = lambdaQuery()
                .eq(DeliveryAddress::getUserId, userId)
                .select(DeliveryAddress::getPriority)
                .orderByAsc(DeliveryAddress::getPriority)
                .list();
        // 设置优先级
        deliveryAddress.setPriority(ownAddresses.get(ownAddresses.size() - 1).getPriority() + 1);
        return RestResult.success(
                save(deliveryAddress)
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult updateDefaultDeliveryAddress(Long userId, Integer deliveryAddressId, Integer priority) {
        // 更新priority之前的所有收货地址的优先级为原本的值加1
        proxy.maintenancePriority(userId, priority);
        // 更新用户的默认收货地址
        userInfoService.lambdaUpdate().eq(UserInfo::getDefaultAddressId, deliveryAddressId).update();
        return RestResult.success(
                // 更新其为默认地址
                lambdaUpdate().eq(DeliveryAddress::getId, deliveryAddressId).update()
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean maintenancePriority(Long userId, Integer priority) {
        List<DeliveryAddress> needUpdate = lambdaQuery()
                .eq(DeliveryAddress::getUserId, userId)
                .select(DeliveryAddress::getId, DeliveryAddress::getPriority)
                .lt(DeliveryAddress::getPriority, priority)
                .list();
        // 不需要更新其他的收货地址的优先级
        if (needUpdate == null || needUpdate.isEmpty()) {
            return true;
        }
        return saveOrUpdateBatch(
                needUpdate.stream().map(d -> {
                    // 更新优先级为原来的优先级加1
                    d.setPriority(d.getPriority() + 1);
                    return d;
                }).collect(Collectors.toList())
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResult deleteDeliveryAddress(Integer deliveryAddressId, Integer priority, Long userId) {
        proxy.maintenancePriority(userId, priority);
        if (priority == 0) {
            // 删除默认地址，修改用户的默认地址
            DeliveryAddress newDefaultDelivery = lambdaQuery().eq(DeliveryAddress::getUserId, userId).eq(DeliveryAddress::getPriority, 1).one();
            // 新的默认地址id
            Integer newDefaultDeliveryAddressId = newDefaultDelivery == null ? null : newDefaultDelivery.getId();
            userInfoService.lambdaUpdate().eq(UserInfo::getId, userId).set(UserInfo::getDefaultAddressId, newDefaultDeliveryAddressId);
        }
        return RestResult.success(
                // 删除对应的收货地址
                lambdaUpdate().eq(DeliveryAddress::getId, deliveryAddressId).remove()
        );
    }
}
