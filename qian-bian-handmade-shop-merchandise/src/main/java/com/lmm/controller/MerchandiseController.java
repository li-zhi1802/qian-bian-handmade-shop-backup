package com.lmm.controller;

import com.lmm.dto.RestResult;
import com.lmm.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@RestController
@RequestMapping("/merchandise")
public class MerchandiseController {
    @Autowired
    private MerchandiseService merchandiseService;

    @GetMapping("/{id}")
    public RestResult getDetailMerchandiseById(@PathVariable("id") Long id) {
        return RestResult.success(merchandiseService.getDetailMerchandiseById(id));
    }
}
