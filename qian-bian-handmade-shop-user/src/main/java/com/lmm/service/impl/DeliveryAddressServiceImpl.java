package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.dto.DeliveryAddressFormDTO;
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

    @Override
    public DeliveryAddress getDefaultDeliveryAddress(Long userId) {
        Long defaultAddressId = userInfoService.lambdaQuery().select(UserInfo::getDefaultAddressId).eq(UserInfo::getId, userId).one().getDefaultAddressId();
        if (defaultAddressId == null) {
            throw new QianBianException("暂无默认收货地址!");
        }
        return getById(defaultAddressId);
    }

    @Override
    public List<DeliveryAddress> listAllDeliveryAddresses(Long userId) {
        return lambdaQuery()
                .eq(DeliveryAddress::getUserId, userId)
                .orderByAsc(DeliveryAddress::getPriority)
                .list();
    }

    @Override
    public Boolean saveDeliveryAddress(DeliveryAddressFormDTO deliveryAddressFormDTO, Long userId) {
        DeliveryAddress deliveryAddress = BeanUtil.copyProperties(deliveryAddressFormDTO, DeliveryAddress.class);
        deliveryAddress.setUserId(userId);
        List<DeliveryAddress> ownAddresses = lambdaQuery()
                .eq(DeliveryAddress::getUserId, userId)
                .select(DeliveryAddress::getPriority)
                .orderByAsc(DeliveryAddress::getPriority)
                .list();
        // 设置优先级
        deliveryAddress.setPriority(ownAddresses.size());
        boolean success = save(deliveryAddress);
        if (success && ownAddresses.isEmpty()) {
            // 设置默认地址
            return userInfoService
                    .lambdaUpdate()
                    .eq(UserInfo::getId, userId)
                    .set(UserInfo::getDefaultAddressId, deliveryAddress.getId())
                    .update();
        }
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateDefaultDeliveryAddress(Long userId, Long deliveryAddressId, Integer priority) {
        // 更新priority之前的所有收货地址的优先级为原本的值加1
        // 更新其他的收货地址的优先级
        List<DeliveryAddress> needUpdate = lambdaQuery()
                .eq(DeliveryAddress::getUserId, userId)
                .select(DeliveryAddress::getId, DeliveryAddress::getPriority)
                .lt(DeliveryAddress::getPriority, priority)
                .list();
        if (needUpdate != null && !needUpdate.isEmpty()) {
            updateBatchById(
                    needUpdate.stream().map(d -> {
                        // 更新优先级为原来的优先级减1
                        d.setPriority(d.getPriority() + 1);
                        return d;
                    }).collect(Collectors.toList())
            );
        }
        // 更新用户的默认收货地址
        userInfoService.lambdaUpdate()
                .eq(UserInfo::getId, userId)
                .set(UserInfo::getDefaultAddressId, deliveryAddressId)
                .update();
        // 更新其为默认地址
        return lambdaUpdate()
                .eq(DeliveryAddress::getId, deliveryAddressId)
                .set(DeliveryAddress::getPriority, 0)
                .update();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteDeliveryAddress(Long deliveryAddressId, Integer priority, Long userId) {
        // 更新其他的收货地址的优先级
        List<DeliveryAddress> needUpdate = lambdaQuery()
                .eq(DeliveryAddress::getUserId, userId)
                .select(DeliveryAddress::getId, DeliveryAddress::getPriority)
                .gt(DeliveryAddress::getPriority, priority)
                .list();
        if (needUpdate != null && !needUpdate.isEmpty()) {
            updateBatchById(
                    needUpdate.stream().map(d -> {
                        // 更新优先级为原来的优先级减1
                        d.setPriority(d.getPriority() - 1);
                        return d;
                    }).collect(Collectors.toList())
            );
        }
        if (priority == 0) {
            // 删除默认地址，修改用户的默认地址
            DeliveryAddress newDefaultDelivery = lambdaQuery().eq(DeliveryAddress::getUserId, userId).eq(DeliveryAddress::getPriority, 1).one();
            // 新的默认地址id
            Long newDefaultDeliveryAddressId = newDefaultDelivery == null ? null : newDefaultDelivery.getId();
            userInfoService.lambdaUpdate().eq(UserInfo::getId, userId).set(UserInfo::getDefaultAddressId, newDefaultDeliveryAddressId);
        }
        // 删除对应的收货地址
        return lambdaUpdate().eq(DeliveryAddress::getId, deliveryAddressId).remove();
    }
}
