package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.entity.Cart;
import com.lmm.vo.CartVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
public interface CartService extends IService<Cart> {

    Boolean addMerchandiseToCart(Long shopId, Long merchandiseId, Long userId);

    Boolean removeMerchandiseFromCart(Long shopId, Long merchandiseId, Long userId);

    List<CartVO> getMyCart(Long userId);

    Boolean updateMerchandiseQuantity(Long shopId, Long merchandiseId, Integer quantity, Long userId);
}
