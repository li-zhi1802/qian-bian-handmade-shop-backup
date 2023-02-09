package com.lmm.controller;

import com.lmm.dto.PageResult;
import com.lmm.dto.RestResult;
import com.lmm.entity.Footmark;
import com.lmm.service.FootmarkService;
import com.lmm.util.SecurityUtil;
import com.lmm.vo.FootmarkVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Api(tags = "足迹的相关接口")
@RestController
@RequestMapping("/footmark")
public class FootmarkController {
    @Autowired
    private FootmarkService footmarkService;

    @ApiOperation("返回登录用户的足迹")
    @GetMapping("/{pageNum}")
    public PageResult<FootmarkVO> listFootmarks(@PathVariable("pageNum") Long pageNum) {
        return footmarkService.listFootmarksByUserId(SecurityUtil.getUser().getId(), pageNum);
    }

    @ApiOperation("删除足迹")
    @DeleteMapping("/{merchandiseId}")
    public RestResult deleteFootmark(@PathVariable("merchandiseId") Long merchandiseId) {
        return RestResult.success(
                footmarkService.lambdaUpdate()
                        .eq(Footmark::getUserId, SecurityUtil.getUser().getId())
                        .eq(Footmark::getMerchandiseId, merchandiseId)
                        .remove()
        );
    }

    @ApiOperation("更新或增加足迹")
    @PutMapping("/{merchandiseId}")
    public RestResult updateOrSaveFootmark(@PathVariable Long merchandiseId) {
        return RestResult.success(
                footmarkService.saveOrUpdate(
                        new Footmark(
                                SecurityUtil.getUser().getId(),
                                merchandiseId,
                                LocalDateTime.now()
                        )
                )
        );
    }
}
