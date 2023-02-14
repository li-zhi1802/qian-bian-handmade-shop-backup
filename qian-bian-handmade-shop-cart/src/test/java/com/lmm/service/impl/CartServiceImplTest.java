package com.lmm.service.impl;

import com.lmm.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CartServiceImplTest {
    @Autowired
    private CartService cartService;

    @Test
    void initRedisCart() {
        cartService.addMerchandiseToCart(1L, 1L, 1L);
    }
}