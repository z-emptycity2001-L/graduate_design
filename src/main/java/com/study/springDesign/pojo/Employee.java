package com.study.springDesign.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String username;
    private String name;
    private String gender;
    private String idCard;
    private String phone;
    private Integer status;
    private Timestamp updateTime;
    private Timestamp createTime;
    private String updateUser;
    private String createUser;
}
