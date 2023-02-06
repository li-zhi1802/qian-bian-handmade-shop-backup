package com.lmm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@TableName("category")
@ApiModel(value = "Category对象", description = "")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("类目的id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("类目名")
    @TableField("name")
    private String name;

    @ApiModelProperty("图标")
    @TableField("icon_uri")
    private String iconUri;

    @ApiModelProperty("简述")
    @TableField("description")
    private String description;

    @ApiModelProperty("父类目编号")
    @TableField("parent_id")
    private Integer parentId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name=" + name +
                ", iconUri=" + iconUri +
                ", description=" + description +
                ", parentId=" + parentId +
                "}";
    }
}
