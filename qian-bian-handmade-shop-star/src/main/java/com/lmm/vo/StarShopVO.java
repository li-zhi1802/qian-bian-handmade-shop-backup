package com.lmm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-09 16:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarShopVO {
    @ApiModelProperty("商铺编号")
    private Integer id;
    @ApiModelProperty("商铺名")
    private String name;
    @ApiModelProperty("商铺头像")
    private String avatarUri;
    @ApiModelProperty("商铺随机5个商品")
    private List<StarMerchandiseVO> merchandises;
}