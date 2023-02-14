package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.client.UserClient;
import com.lmm.dto.AddShopDTO;
import com.lmm.dto.UpdateShopDTO;
import com.lmm.dto.UserDTO;
import com.lmm.entity.Shop;
import com.lmm.entity.UserInfo;
import com.lmm.exception.QianBianException;
import com.lmm.mapper.ShopMapper;
import com.lmm.service.ShippingAddressService;
import com.lmm.service.ShopService;
import com.lmm.vo.ShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {
    @Autowired
    private ShippingAddressService shippingAddressService;
    @Autowired
    private UserClient userClient;

    @Override
    public ShopVO getShopDetailById(Long shopId) {
        Shop shop = getById(shopId);
        ShopVO shopVO = BeanUtil.copyProperties(shop, ShopVO.class);
        UserInfo shopKeeper = userClient.findUserById(shop.getShopKeeperId());
        shopVO.setShopKeeper(BeanUtil.copyProperties(shopKeeper, UserDTO.class));
        shopVO.setDefaultAddress(shippingAddressService.defaultShippingAddress(shopId));
        return shopVO;
    }

    @Override
    public Boolean addShop(AddShopDTO addShopDTO, Long userId) {
        Shop shop = BeanUtil.copyProperties(addShopDTO, Shop.class);
        shop.setShopKeeperId(userId);
        boolean save = save(shop);
        if (!save) {
            throw new QianBianException("保存店铺失败");
        }
        return userClient.updateHasShop(userId, shop.getId());
    }

    @Override
    public Boolean updateShopInfo(UpdateShopDTO updateShopDTO, Long userId) {
        return saveOrUpdate(BeanUtil.copyProperties(updateShopDTO, Shop.class));
    }
}
