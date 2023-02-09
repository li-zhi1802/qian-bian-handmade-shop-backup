package com.lmm.controller;

import com.lmm.dto.PageResult;
import com.lmm.dto.RestResult;
import com.lmm.service.StarMerchandiseService;
import com.lmm.util.SecurityUtil;
import com.lmm.vo.StarMerchandiseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "收藏商品的相关接口")
@RequestMapping("/star-merchandise")
public class StarMerchandiseController {
    @Autowired
    private StarMerchandiseService starMerchandiseService;

    @ApiOperation("将商品添加至收藏列表或者在收藏列表中删除此商品")
    @PutMapping("/{merchandiseId}")
    public RestResult starMerchandise(@PathVariable("merchandiseId") Long merchandiseId) {
        return RestResult.success(starMerchandiseService.addOrRemoveMerchandise(merchandiseId, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("分页得到登录用户所有的收藏的商品")
    @GetMapping("/{pageNum}")
    public PageResult<StarMerchandiseVO> listStarMerchandiseByPage(@PathVariable("pageNum") Long pageNum) {
        return starMerchandiseService.listStarMerchandiseByPage(pageNum, SecurityUtil.getUser().getId());
    }

    @ApiOperation("删除指定收藏商品")
    @DeleteMapping
    public RestResult deleteStarMerchandises(@RequestBody List<Long> merchandiseId) {
        return RestResult.success(starMerchandiseService.deleteStarMerchandises(merchandiseId, SecurityUtil.getUser().getId()));
    }
}
