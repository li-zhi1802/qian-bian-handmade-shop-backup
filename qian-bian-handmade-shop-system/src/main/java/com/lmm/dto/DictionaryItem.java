package com.lmm.dto;

import lombok.Data;

import java.util.Map;

@Data
public class DictionaryItem {
    private Integer id;
    private String description;
    private Map<String, String> dictionary;
}