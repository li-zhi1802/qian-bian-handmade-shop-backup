package com.lmm.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmm.constant.SystemError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-08 16:42
 **/
@Data
@ApiModel("分页查询的结果")
@AllArgsConstructor
public class PageResult<T> {
    @ApiModelProperty("此页的数据")
    private List<T> data;

    @ApiModelProperty("当前页")
    private Long pageNum;

    @ApiModelProperty("总数据量")
    private Long total;

    @ApiModelProperty("是否出现业务错误，如果出现了错误就将错误信息放入message")
    private Boolean success;

    @ApiModelProperty("错误信息")
    private String errorMessage;

    public static <T> PageResult<T> success(Page<T> pageQuery) {
        return new PageResult<T>(
                pageQuery.getRecords(),
                pageQuery.getCurrent(),
                pageQuery.getTotal(),
                true,
                null
        );
    }

    public static <T> PageResult<T> success(List<T> records, Long pageNum, Long total) {
        return new PageResult<T>(
                records,
                pageNum,
                total,
                true,
                null
        );
    }

    public static <T> PageResult<T> fail(SystemError systemError) {
        return new PageResult<T>(
                null,
                null,
                null,
                true,
                systemError.getErrorMessage()
        );
    }
}
