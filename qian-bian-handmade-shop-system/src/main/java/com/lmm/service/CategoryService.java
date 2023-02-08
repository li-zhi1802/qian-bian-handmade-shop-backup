package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.entity.Category;
import com.lmm.vo.CategoryVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
public interface CategoryService extends IService<Category> {

    List<CategoryVO> queryChildCategory(Integer topParentId);
}
