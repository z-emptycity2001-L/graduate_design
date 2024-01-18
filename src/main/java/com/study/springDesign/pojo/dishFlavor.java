package com.study.springDesign.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class dishFlavor {
    private Long id;

    private Long dishId;

    private String name;

    private String value;

    private Date createTime;

    private Date updateTime;

    private Long createUser;

    private Long updateUser;

    private Integer isDeleted;
}