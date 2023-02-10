package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.client.MerchandiseClient;
import com.lmm.client.ShopClient;
import com.lmm.dto.CartDTO;
import com.lmm.entity.Cart;
import com.lmm.entity.Merchandise;
import com.lmm.entity.Shop;
import com.lmm.exception.QianBianException;
import com.lmm.mapper.CartMapper;
import com.lmm.service.CartService;
import com.lmm.vo.CartItem;
import com.lmm.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lmm.constant.RedisConstant.CART_KEY;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MerchandiseClient merchandiseClient;
    @Autowired
    private ShopClient shopClient;

    @Override
    public Boolean addMerchandiseToCart(Long shopId, Long merchandiseId, Long userId) {
        // 购物车结构是按商家分类，然后商品具体信息用json序列化保存
        String cacheKey = CART_KEY + userId;
        // 先看看之前购物车有没有这家店的商品
        // 之前就没有这家店
        if (BooleanUtil.isFalse(stringRedisTemplate.opsForHash().hasKey(cacheKey, shopId))) {
            List<CartDTO> cartDTOs = List.of(new CartDTO(merchandiseId, 1));
            // 放入缓存
            stringRedisTemplate.opsForHash().put(cacheKey, shopId, JSONUtil.toJsonStr(cartDTOs));
            // 落库
            return save(new Cart(userId, merchandiseId, 1));
        }
        // 购物车里面本来就有这个商铺
        List<CartDTO> whole = JSONUtil.toList((String) stringRedisTemplate.opsForHash().get(cacheKey, shopId), CartDTO.class);
        List<CartDTO> collect = whole.stream()
                .filter(c -> merchandiseId.equals(c.getMerchandiseId()))
                .collect(Collectors.toList());
        if (collect.isEmpty()) {
            // 说明原来没有这个商品，要新加入一个
            whole.add(new CartDTO(merchandiseId, 1));
            // 写回缓存
            stringRedisTemplate.opsForHash().put(cacheKey, shopId, JSONUtil.toJsonStr(whole));
            // 落库
            return save(new Cart(userId, merchandiseId, 1));
        }
        // 原来就有这个商品，只需要数量加一即可
        CartDTO updateCart = collect.get(0);
        updateCart.setQuantity(1 + updateCart.getQuantity());
        // 写回缓存
        stringRedisTemplate.opsForHash().put(cacheKey, shopId, JSONUtil.toJsonStr(whole));
        // 更新数据库
        return lambdaUpdate().eq(Cart::getUserId, userId).eq(Cart::getMerchandiseId, merchandiseId).set(Cart::getQuantity, updateCart.getQuantity()).update();
    }

    @Override
    public Boolean removeMerchandiseFromCart(Long shopId, Long merchandiseId, Long userId) {
        // 购物车结构是按商家分类，然后商品具体信息用json序列化保存
        String cacheKey = CART_KEY + userId;
        // 先看看之前购物车有没有这家店的商品
        // 之前就没有这家店
        if (BooleanUtil.isFalse(stringRedisTemplate.opsForHash().hasKey(cacheKey, shopId))) {
            throw new QianBianException("缓存出错，请重新");
        }
        // 购物车的同一家店铺的所有商品
        List<CartDTO> cartDTOs = JSONUtil.toList((String) stringRedisTemplate.opsForHash().get(cacheKey, shopId), CartDTO.class);
        // 删除后
        List<CartDTO> leftCartDTOs = cartDTOs.stream().filter(c -> !c.getMerchandiseId().equals(merchandiseId)).collect(Collectors.toList());
        if (leftCartDTOs.isEmpty()) {
            // 说明购物车中原本这家店也就一个商品
            // 把这一项删除
            stringRedisTemplate.opsForHash().delete(cacheKey, shopId);
            // 落库
            return lambdaUpdate().eq(Cart::getUserId, userId).eq(Cart::getMerchandiseId, merchandiseId).remove();
        }
        // 购物车中同店铺有多件商品，更新即可
        stringRedisTemplate.opsForHash().put(cacheKey, shopId, JSONUtil.toJsonStr(leftCartDTOs));
        // 落库
        return lambdaUpdate().eq(Cart::getUserId, userId).eq(Cart::getMerchandiseId, merchandiseId).remove();
    }

    @Override
    public List<CartVO> getMyCart(Long userId) {
        String cacheKey = CART_KEY + userId;
        List<CartVO> cartVOs = new LinkedList<>();
        Map<Object, Object> cartEntries = stringRedisTemplate.opsForHash().entries(cacheKey);
        for (Map.Entry<Object, Object> cartEntry : cartEntries.entrySet()) {
            Long shopId = Long.parseLong((String) cartEntry.getKey());
            List<CartItem> cartItems = JSONUtil.toList((String) cartEntry.getValue(), CartDTO.class).stream().map(ct -> {
                Merchandise merchandiseDetail = merchandiseClient.getMerchandiseById(ct.getMerchandiseId());
                CartItem cartItem = BeanUtil.copyProperties(merchandiseDetail, CartItem.class);
                cartItem.setQuantity(ct.getQuantity());
                return cartItem;
            }).collect(Collectors.toList());
            Shop shopDetail = shopClient.getShopById(shopId);
            CartVO cartVO = BeanUtil.copyProperties(shopDetail, CartVO.class);
            cartVO.setCartItems(cartItems);
            cartVO.setVouchers(null);
            cartVOs.add(cartVO);
        }
        return cartVOs;
    }

    @Override
    public Boolean updateMerchandiseQuantity(Long shopId, Long merchandiseId, Integer quantity, Long userId) {
        String cacheKey = CART_KEY + userId;
        List<CartDTO> cartDTOs = JSONUtil.toList((String) stringRedisTemplate.opsForHash().get(cacheKey, shopId), CartDTO.class);
        for (CartDTO ct : cartDTOs) {
            if (ct.getMerchandiseId().equals(merchandiseId)) {
                ct.setQuantity(quantity);
                break;
            }
        }
        // 更新缓存
        stringRedisTemplate.opsForHash().put(cacheKey, shopId, JSONUtil.toJsonStr(cartDTOs));
        // 落库
        return lambdaUpdate().eq(Cart::getUserId, userId).eq(Cart::getMerchandiseId, merchandiseId).eq(Cart::getQuantity, quantity).update();
    }
}
