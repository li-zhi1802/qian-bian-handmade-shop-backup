package com.lmm.controller;

import com.alipay.api.AlipayApiException;
import com.lmm.dto.RestResult;
import com.lmm.service.AlipayService;
import com.lmm.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 芝麻
 * @date : 2023-02-11 20:50
 **/
@Api(tags = "阿里支付的接口")
@RestController
@RequestMapping("/alipay")
public class AlipayController {
    @Autowired
    private AlipayService alipayService;

    @ApiOperation("得到支付的body体，前端的地址直接写这个地址就可以跳转支付界面")
    @GetMapping("{generateOrderId}")
    public String getPayBody(@PathVariable("generateOrderId") String generateOrderId) throws AlipayApiException {
        return alipayService.getPayBody(generateOrderId, SecurityUtil.getUser().getId());
    }

    @ApiOperation("支付通知，自己会调的")
    @PostMapping("/notify")
    public void alipayNotify(HttpServletRequest request) {
        Map<String, String[]> requestParams = request.getParameterMap();
        Map<String, String> params = new HashMap<>(requestParams.size());
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        String generateOrderId = params.get("out_trade_no");
        alipayService.payNotify(generateOrderId, Long.parseLong(params.get("body")));
    }

    @ApiOperation("退款")
    @PostMapping("/refund/{orderId}")
    public RestResult refund(@PathVariable String orderId) throws AlipayApiException {
        return RestResult.success(alipayService.refund(orderId));
    }
}
