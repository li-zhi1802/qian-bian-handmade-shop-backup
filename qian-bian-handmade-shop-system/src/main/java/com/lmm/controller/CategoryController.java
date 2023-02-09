package com.lmm.controller;

import com.lmm.service.CategoryService;
import com.lmm.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "类目的相关接口")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation("返回指定父类目的子目录，若不指定，就是返回顶级目录")
    @GetMapping({"/{topParentId}", ""})
    public List<CategoryVO> dictionary(@PathVariable(value = "topParentId", required = false) Integer topParentId) {
        return categoryService.queryChildCategory(topParentId == null ? 1 : topParentId);
    }
}
