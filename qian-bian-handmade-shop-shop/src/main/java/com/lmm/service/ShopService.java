package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.AddShopDTO;
import com.lmm.dto.UpdateShopDTO;
import com.lmm.entity.Shop;
import com.lmm.vo.ShopVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
public interface ShopService extends IService<Shop> {

    ShopVO getShopDetailById(Long shopId);

    Boolean addShop(AddShopDTO addShopDTO, Long userId);

    Boolean updateShopInfo(UpdateShopDTO updateShopDTO, Long userId);
}
