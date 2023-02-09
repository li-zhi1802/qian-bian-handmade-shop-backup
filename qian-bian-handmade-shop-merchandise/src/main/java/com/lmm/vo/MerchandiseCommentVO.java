package com.lmm.vo;

import com.lmm.dto.UserDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MerchandiseCommentVO {
    @ApiModelProperty("评论id")
    private Long id;
    @ApiModelProperty("评论用户信息")
    private UserDTO user;
    @ApiModelProperty("发布时间")
    private LocalDateTime publishTime;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("子评论")
    private List<MerchandiseCommentVO> childMerchandiseComments;
    @ApiModelProperty("描述打分")
    private BigDecimal descriptionScore;
    @ApiModelProperty("服务打分")
    private BigDecimal serviceScore;
    @ApiModelProperty("物流打分")
    private BigDecimal logisticsScore;
    @ApiModelProperty("关联的图片")
    private List<String> picUris;
}
