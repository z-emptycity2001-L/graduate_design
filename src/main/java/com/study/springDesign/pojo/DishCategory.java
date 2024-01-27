package com.study.springDesign.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishCategory {
    private Long categoryId;

    private Integer type;

    private String categoryName;

    private Date createTime;

    private Date updateTime;

    private Long createUser;

    private Long updateUser;
}