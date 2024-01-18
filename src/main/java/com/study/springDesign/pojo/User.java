package com.study.springDesign.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String nickname;
    private String password;
    private String phone;
    private String sex;
    private String avatar;
    private String address;
    private String wechatName;
    private Timestamp createTime;
    private Timestamp updateTime;
}
