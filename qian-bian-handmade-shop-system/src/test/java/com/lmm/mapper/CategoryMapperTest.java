package com.lmm.mapper;

import com.lmm.vo.CategoryVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryMapperTest {
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void queryChildCategory() {
        CategoryVO categoryVO = categoryMapper.queryChildCategory(2);
        System.out.println(categoryVO.getId());
        categoryVO.getChildCategories().forEach(System.out::println);
    }
}