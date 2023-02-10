package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.DeliveryAddressFormDTO;
import com.lmm.entity.DeliveryAddress;

import java.util.List;

public interface DeliveryAddressService extends IService<DeliveryAddress> {

    /**
     * 返回指定用户的默认收货地址
     *
     * @param userId
     * @return
     */
    DeliveryAddress getDefaultDeliveryAddress(Long userId);

    /**
     * 返回指定用户的所有收货地址
     *
     * @param userId
     * @return
     */
    List<DeliveryAddress> listAllDeliveryAddresses(Long userId);

    /**
     * 保存收货地址
     *
     * @param deliveryAddressFormDTO
     * @param userId
     * @return
     */
    Boolean saveDeliveryAddress(DeliveryAddressFormDTO deliveryAddressFormDTO, Long userId);

    /**
     * 更新默认地址
     *
     * @param userId
     * @param deliveryAddressId
     * @param priority
     * @return
     */
    Boolean updateDefaultDeliveryAddress(Long userId, Integer deliveryAddressId, Integer priority);

    /**
     * 删除收货地址
     *
     * @param deliveryAddressId
     * @param priority
     * @param userId
     * @return
     */
    Boolean deleteDeliveryAddress(Integer deliveryAddressId, Integer priority, Long userId);
}
