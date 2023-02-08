package com.lmm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmm.entity.Category;
import com.lmm.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    CategoryVO queryChildCategory(@Param("topParentId") Integer topParentId);
}
