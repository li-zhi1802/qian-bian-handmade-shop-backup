package com.lmm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmm.entity.Merchandise;
import com.lmm.service.MerchandiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-08 17:08
 **/
@Api(tags = "远程调用接口")
@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private MerchandiseService merchandiseService;

    @ApiOperation("得到商品信息")
    @GetMapping("/{id}")
    @Cacheable(cacheNames = "feign:merchandise:", key = "args[0]")
    public Merchandise getMerchandiseById(@PathVariable("id") Long id) {
        return merchandiseService.getById(id);
    }

    @ApiOperation("返回指定商铺的5条商品")
    @GetMapping("/randomMerchandise/{shopId}")
    public List<Merchandise> randomMerchandiseByShopId(@PathVariable Long shopId) {
        return merchandiseService.page(
                // 直接拿第一页的5条记录
                new Page<>(1, 5),
                new LambdaQueryWrapper<Merchandise>()
                        .eq(Merchandise::getShopId, shopId)
                        // 上架的
                        .eq(Merchandise::getValid, 1)
        ).getRecords();
    }
}
