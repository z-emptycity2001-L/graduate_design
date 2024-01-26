package com.study.springDesign.service.impl;

import com.study.springDesign.mapper.UserMapper;
import com.study.springDesign.pojo.User;
import com.study.springDesign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int registerUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int selectUserNumber() {
        return userMapper.countUser();
    }

    @Override
    public User searchUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }
}
