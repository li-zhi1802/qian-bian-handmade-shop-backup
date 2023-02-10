package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.AddShippingAddressDTO;
import com.lmm.entity.ShippingAddress;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
public interface ShippingAddressService extends IService<ShippingAddress> {

    Boolean addShippingAddress(AddShippingAddressDTO addShippingAddressDTO, Long userId);

    Boolean deleteShippingAddress(Long shopId, Long shippingAddressId, Long userId);

    List<ShippingAddress> allShippingAddress(Long shopId, Long userId);

    Boolean updateDefaultShippingAddress(Long shopId, Long shippingAddressId, Integer priority, Long userId);
}
