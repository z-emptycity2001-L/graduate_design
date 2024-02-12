package com.study.springDesign.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String id;
    private String username;
    private String name;
    private String gender;
    private String idCard;
    private String phone;
    private String status;
    private Date updateTime;
    private Date createTime;
    private String updateUser;
    private String createUser;
}
