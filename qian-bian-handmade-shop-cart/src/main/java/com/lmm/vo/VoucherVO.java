package com.lmm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherVO {
    @ApiModelProperty("优惠券编号")
    private Integer id;
    @ApiModelProperty("优惠券开始发放时间")
    private Date startTime;
    @ApiModelProperty("优惠券结束发放时间")
    private Date endTime;
    @ApiModelProperty("优惠券有效开始时间")
    private Date validUseStartTime;
    @ApiModelProperty("优惠券有效结束时间")
    private Date validUseEndTime;
    @ApiModelProperty("优惠券名 满minMoney减decreaseMoney券")
    private String title;
    @ApiModelProperty("满多少才可以用")
    private Integer minMoney;
    @ApiModelProperty("可以减多少")
    private Integer decreaseMoney;
    @ApiModelProperty("配额")
    private Integer quota;
    @ApiModelProperty("每人限领")
    private Integer limit;
    @ApiModelProperty("库存")
    private Integer stock;
}
