package com.study.springDesign.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private String avatar;
    private String address;
    private String wechatName;
    private Timestamp createTime;
    private Timestamp updateTime;
}
