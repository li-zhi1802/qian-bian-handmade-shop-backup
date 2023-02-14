package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.client.ShopClient;
import com.lmm.client.SystemClient;
import com.lmm.client.UserClient;
import com.lmm.client.VoucherClient;
import com.lmm.dto.PublishMerchandiseDTO;
import com.lmm.dto.UpdateMerchandiseDTO;
import com.lmm.dto.UserDTO;
import com.lmm.entity.Merchandise;
import com.lmm.entity.Shop;
import com.lmm.exception.QianBianException;
import com.lmm.mapper.MerchandiseMapper;
import com.lmm.service.MerchandiseService;
import com.lmm.vo.MerchandiseVO;
import com.lmm.vo.ShippingAddressVO;
import com.lmm.vo.ShopVO;
import com.lmm.vo.VoucherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
public class MerchandiseServiceImpl extends ServiceImpl<MerchandiseMapper, Merchandise> implements MerchandiseService {
    @Autowired
    private ShopClient shopClient;
    @Autowired
    private SystemClient systemClient;
    @Autowired
    private VoucherClient voucherClient;
    @Autowired
    private UserClient userClient;

    @Override
    @Cacheable(cacheNames = "merchandise:detail:", key = "args[0]")
    public MerchandiseVO getDetailMerchandiseById(Long id) {
        Merchandise merchandise = getById(id);
        MerchandiseVO merchandiseVO = BeanUtil.copyProperties(merchandise, MerchandiseVO.class);
        merchandiseVO.setDefaultShippingAddress(BeanUtil.copyProperties(shopClient.defaultShippingAddress(merchandise.getShopId()), ShippingAddressVO.class));
        merchandiseVO.setCategory(systemClient.getCategoryById(merchandise.getCategoryId()));
        merchandiseVO.setPicUris(JSONUtil.toList(merchandise.getPicUris(), String.class));
        merchandiseVO.setVouchers(
                voucherClient.listShopVouchers(merchandise.getShopId(), 1L).stream()
                        .map(v -> BeanUtil.copyProperties(v, VoucherVO.class))
                        .collect(Collectors.toList())
        );
        Shop shop = shopClient.getShopById(merchandise.getShopId());
        ShopVO shopVO = BeanUtil.copyProperties(shop, ShopVO.class);
        shopVO.setShopKeeper(BeanUtil.copyProperties(userClient.findUserById(shop.getShopKeeperId()), UserDTO.class));
        merchandiseVO.setShop(shopVO);
        return merchandiseVO;
    }

    @Override
    public Boolean publishMerchandise(PublishMerchandiseDTO publishMerchandiseDTO, Long userId) {
        Long shopId = publishMerchandiseDTO.getShopId();
        if (shopId == null) {
            throw new QianBianException("发布商品需要提供店铺id");
        }
        if (!userClient.findUserById(userId).getShopId().equals(shopId)) {
            throw new QianBianException("您不是店主，请不要操作此店铺的相关信息");
        }
        if (publishMerchandiseDTO.getPicUris() == null || publishMerchandiseDTO.getPicUris().isEmpty()) {
            throw new QianBianException("请至少上传一张商品照片");
        }
        Merchandise merchandise = BeanUtil.copyProperties(publishMerchandiseDTO, Merchandise.class);
        merchandise.setPicUris(JSONUtil.toJsonStr(publishMerchandiseDTO.getPicUris()));
        return save(merchandise);
    }

    @Override
    public Boolean lowerShelfMerchandise(Long merchandiseId) {
        // 更新valid
        return lambdaUpdate()
                .eq(Merchandise::getId, merchandiseId)
                .set(Merchandise::getValid, 0)
                .update();
    }

    @Override
    public Boolean updateMerchandise(UpdateMerchandiseDTO updateMerchandiseDTO) {
        return updateById(BeanUtil.copyProperties(updateMerchandiseDTO, Merchandise.class));
    }
}
