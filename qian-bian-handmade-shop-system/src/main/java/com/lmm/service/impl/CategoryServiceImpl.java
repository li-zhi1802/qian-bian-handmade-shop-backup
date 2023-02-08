package com.lmm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.entity.Category;
import com.lmm.mapper.CategoryMapper;
import com.lmm.service.CategoryService;
import com.lmm.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Cacheable(cacheNames = "system", key = "'category:'+args[0]")
    @Override
    public List<CategoryVO> queryChildCategory(Integer topParentId) {
        return categoryMapper.queryChildCategory(topParentId).getChildCategories();
    }
}
