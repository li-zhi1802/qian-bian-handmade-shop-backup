package com.lmm.vo;

import com.lmm.dto.UserDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : 芝麻
 * @date : 2023-02-09 15:05
 **/
@Data
public class ShopVO {
    @ApiModelProperty("商铺id")
    private Long id;
    @ApiModelProperty("商铺名")
    private String name;
    @ApiModelProperty("描述平均分")
    private BigDecimal descriptionAvgScore;
    @ApiModelProperty("服务平均分")
    private BigDecimal serviceAvgScore;
    @ApiModelProperty("物流平均分")
    private BigDecimal logisticsAvgScore;
    @ApiModelProperty("店铺头像")
    private String avatarUri;
    @ApiModelProperty("店主信息")
    private UserDTO shopKeeper;
}
