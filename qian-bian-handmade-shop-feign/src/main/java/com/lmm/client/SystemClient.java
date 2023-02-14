package com.lmm.client;

import com.lmm.entity.Category;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "系统Feign客户端")
@FeignClient("qian-bian-system")
@RequestMapping("/system")
public interface SystemClient {
    /**
     * 得到指定分类的信息
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/category/specify/{categoryId}")
    Category getCategoryById(@PathVariable("categoryId") Integer categoryId);
}
