package com.study.springDesign.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    private Long id;

    private String name;

    private Long categoryId;//餐品类别

    private BigDecimal price;

    private String code;//已出售数量

    private String image;

    private String description;//餐品描述

    private Integer status;//餐品状态 0 代表停售

    private Date createTime;

    private Date updateTime;

    private Long createUser;

    private Long updateUser;

    private Integer isDeleted;

}