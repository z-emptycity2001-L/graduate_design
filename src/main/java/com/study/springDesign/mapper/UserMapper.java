package com.study.springDesign.mapper;

import com.study.springDesign.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertUser(User user);
}
