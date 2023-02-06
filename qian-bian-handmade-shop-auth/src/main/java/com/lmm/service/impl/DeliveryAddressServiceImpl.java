package com.lmm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.entity.DeliveryAddress;
import com.lmm.mapper.DeliveryAddressMapper;
import com.lmm.service.DeliveryAddressService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryAddressServiceImpl extends ServiceImpl<DeliveryAddressMapper, DeliveryAddress> implements DeliveryAddressService {

}
