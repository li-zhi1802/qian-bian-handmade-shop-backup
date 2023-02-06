package com.lmm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.entity.Category;
import com.lmm.mapper.CategoryMapper;
import com.lmm.service.CategoryService;
import org.springframework.stereotype.Service;

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

}
