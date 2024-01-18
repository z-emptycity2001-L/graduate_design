package com.study.springDesign.controller;

import com.study.springDesign.Util.R;
import com.study.springDesign.pojo.User;
import com.study.springDesign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public R register(@RequestBody User user){
        Integer count = userService.registerUser(user);
        Timestamp timestamp = Timestamp.valueOf(String.valueOf(new Date()));
        System.out.println(timestamp);
        if(count==null||count!=1){
            return R.success("注册失败，请稍后再试") ;
        }
        return R.success("注册成功");
    }

    @GetMapping("/count")
    public R getUserNumber(){
        int i = userService.selectUserNumber();
        HashMap<String,Integer> map=new HashMap<>();
        map.put("data",i);
//        return i;
        return R.success(null,map);
    }
    @PostMapping("/login")
    public R login(@RequestBody User user){
        List<User> users = userService.searchUser(user);
        return R.success("登陆成功",users);
    }

}
