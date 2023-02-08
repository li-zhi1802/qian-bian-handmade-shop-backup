package com.lmm.controller;

import com.lmm.entity.Merchandise;
import com.lmm.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 芝麻
 * @date : 2023-02-08 17:08
 **/
@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private MerchandiseService merchandiseService;

    /**
     * 得到商品信息
     *
     * @param id
     * @return
     */
    @GetMapping("/feign/detail/{id}")
    public Merchandise getMerchandiseById(@PathVariable("id") Long id) {
        return merchandiseService.getById(id);
    }
}
