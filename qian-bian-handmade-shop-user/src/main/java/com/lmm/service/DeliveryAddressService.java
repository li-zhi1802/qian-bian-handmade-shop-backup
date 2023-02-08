package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.DeliveryAddressFormDTO;
import com.lmm.dto.RestResult;
import com.lmm.entity.DeliveryAddress;

public interface DeliveryAddressService extends IService<DeliveryAddress> {

    /**
     * 返回指定用户的默认收货地址
     *
     * @param userId
     * @return
     */
    RestResult getDefaultDeliveryAddress(Long userId);

    /**
     * 返回指定用户的所有收货地址
     *
     * @param userId
     * @return
     */
    RestResult listAllDeliveryAddresses(Long userId);

    /**
     * 保存收货地址
     *
     * @param deliveryAddressFormDTO
     * @param userId
     * @return
     */
    RestResult saveDeliveryAddress(DeliveryAddressFormDTO deliveryAddressFormDTO, Long userId);

    /**
     * 更新默认地址
     *
     * @param userId
     * @param deliveryAddressId
     * @param priority
     * @return
     */
    RestResult updateDefaultDeliveryAddress(Long userId, Integer deliveryAddressId, Integer priority);

    /**
     * 删除收货地址
     *
     * @param deliveryAddressId
     * @param priority
     * @param userId
     * @return
     */
    RestResult deleteDeliveryAddress(Integer deliveryAddressId, Integer priority, Long userId);

    /**
     * 删除指定用户的指定优先级的收货地址，并且维护指定优先级之前的收货地址的优先级
     *
     * @param userId
     * @param priority
     * @return
     */
    boolean maintenancePriority(Long userId, Integer priority);
}
