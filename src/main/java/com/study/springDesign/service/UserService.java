package com.study.springDesign.service;

import com.study.springDesign.Util.R;
import com.study.springDesign.pojo.User;

import java.util.List;

public interface UserService {
    int registerUser(User user) ;
    int selectUserNumber();

    User searchUserByUsername(String username);
}
