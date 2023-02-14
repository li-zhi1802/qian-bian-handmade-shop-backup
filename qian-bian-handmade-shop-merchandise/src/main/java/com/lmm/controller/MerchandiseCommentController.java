package com.lmm.controller;

import com.lmm.dto.PublishMerchandiseCommentDTO;
import com.lmm.dto.RestResult;
import com.lmm.dto.ReviewMerchandiseCommentDTO;
import com.lmm.service.MerchandiseCommentService;
import com.lmm.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Api(tags = "商品评论的接口")
@RestController
@RequestMapping("/merchandise-comment")
public class MerchandiseCommentController {
    @Autowired
    private MerchandiseCommentService merchandiseCommentService;

    @ApiOperation("分页返回指定商品的评论")
    @GetMapping("/{merchandiseId}/{pageNum}")
    public RestResult listCommentsByMerchandiseId(@PathVariable("merchandiseId") Long merchandiseId, @PathVariable("pageNum") Long pageNum) {
        return RestResult.success(merchandiseCommentService.listCommentsByMerchandiseId(merchandiseId, pageNum));
    }

    @ApiOperation("用户通过订单评论")
    @PostMapping("/publish")
    public RestResult publishComment(@RequestBody PublishMerchandiseCommentDTO publishMerchandiseCommentDTO) {
        return RestResult.success(merchandiseCommentService.publishComment(publishMerchandiseCommentDTO, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("用户追评")
    @PostMapping("/review")
    public RestResult reviewComment(@RequestBody ReviewMerchandiseCommentDTO reviewMerchandiseCommentDTO) {
        return RestResult.success(merchandiseCommentService.reviewComment(reviewMerchandiseCommentDTO, SecurityUtil.getUser().getId()));
    }
}
