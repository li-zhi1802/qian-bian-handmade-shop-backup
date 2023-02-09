package com.lmm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : 芝麻
 * @date : 2023-02-09 15:07
 **/
@Data
public class VoucherVO {
    @ApiModelProperty("优惠券id")
    private Long id;
    @ApiModelProperty("优惠券名称")
    private String title;
    @ApiModelProperty("每人限领")
    private Integer limit;
    @ApiModelProperty("配额")
    private Integer quota;
    @ApiModelProperty("库存")
    private Integer stock;
    @ApiModelProperty("领取开始时间")
    private LocalDateTime startTime;
    @ApiModelProperty("领取结束时间")
    private LocalDateTime endTime;
    @ApiModelProperty("有效期开始时间")
    private LocalDateTime validUseStartTime;
    @ApiModelProperty("有效期结束时间")
    private LocalDateTime validUseEndTime;
    @ApiModelProperty("优惠券状态")
    private String state;
}
