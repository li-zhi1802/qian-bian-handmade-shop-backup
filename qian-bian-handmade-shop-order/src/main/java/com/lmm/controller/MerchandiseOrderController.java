package com.lmm.controller;

import com.lmm.dto.MerchandiseOrderDTO;
import com.lmm.dto.PageResult;
import com.lmm.dto.RestResult;
import com.lmm.service.MerchandiseOrderService;
import com.lmm.util.SecurityUtil;
import com.lmm.vo.MerchandiseOrderVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@RestController
@RequestMapping("/merchandise-order")
public class MerchandiseOrderController {
    @Autowired
    private MerchandiseOrderService merchandiseOrderService;

    @ApiOperation("返回生成的用来支付的大订单的编号，返回订单编号")
    @PostMapping("/generate")
    public RestResult generateOrder(@RequestBody List<MerchandiseOrderDTO> ordersToBeGenerated) {
        return RestResult.success(merchandiseOrderService.generateOrders(ordersToBeGenerated, SecurityUtil.getUser().getId()));
    }

    @ApiOperation("得到登录用户指定状态的订单，不传就是所有")
    @GetMapping("/{pageNum}")
    public PageResult<MerchandiseOrderVO> listOrderByPage(@PathVariable("pageNum") Long pageNum, @RequestParam(value = "orderState", required = false) String orderState) {
        return merchandiseOrderService.listOrderByPage(pageNum, orderState, SecurityUtil.getUser().getId());
    }
}
