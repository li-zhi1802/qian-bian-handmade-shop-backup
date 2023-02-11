package com.lmm.client;

import com.lmm.entity.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("qian-bian-shop")
@RequestMapping("/system")
public interface SystemClient {
    @GetMapping("/category/{categoryId}")
    Category getCategoryById(@PathVariable("categoryId") Integer categoryId);
}
