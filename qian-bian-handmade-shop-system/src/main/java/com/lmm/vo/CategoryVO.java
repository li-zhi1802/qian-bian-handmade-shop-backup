package com.lmm.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CategoryVO {
    private Integer id;
    private String name;
    private String iconUri;
    private String description;
    private List<CategoryVO> childCategories;
}
