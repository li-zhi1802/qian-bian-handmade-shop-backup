package com.lmm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : 芝麻
 * @date : 2023-02-10 13:19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVoucherDTO {
    @ApiModelProperty("优惠券编号")
    private Long id;
    @ApiModelProperty("最小金额达到多少可以使用")
    private Integer minMoney;
    @ApiModelProperty("可以减多少")
    private Integer decreaseMoney;
    @ApiModelProperty("每人限领多少")
    private Integer limit;
    @ApiModelProperty("配额")
    private Integer quota;
    @ApiModelProperty("开始领取时间")
    private LocalDateTime startTime;
    @ApiModelProperty("结束领取时间")
    private LocalDateTime endTime;
    @ApiModelProperty("生效开始时间")
    private LocalDateTime validUseStartTime;
    @ApiModelProperty("生效结束时间")
    private LocalDateTime validUseEndTime;
}
