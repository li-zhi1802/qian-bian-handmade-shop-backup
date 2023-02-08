package com.lmm.controller;

import com.lmm.dto.DictionaryItem;
import com.lmm.service.DictionaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ApiOperation("系统字典表的相关接口")
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @ApiOperation("返回系统字典表")
    @GetMapping
    public Map<String, DictionaryItem> dictionary() {
        return dictionaryService.queryAll();
    }
}
