package com.study.springDesign.mapper;

import com.study.springDesign.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insertUser(User user);
    int countUser();

    List<User> selectUserByNicknameAndPassword(User user);
}
