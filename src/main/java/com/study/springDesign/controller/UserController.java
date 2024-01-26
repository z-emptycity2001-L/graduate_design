package com.study.springDesign.controller;

import com.study.springDesign.Util.JwtTokenUtil;
import com.study.springDesign.Util.R;
import com.study.springDesign.pojo.Login;
import com.study.springDesign.pojo.User;
import com.study.springDesign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${user.defaultPassword}")
    private String defaultPassword;
    @PostMapping("/register")
    public R register(@RequestBody User user){
         if(!Objects.isNull(userService.searchUserByUsername(user.getUsername()))){
             return R.error("注册失败，该用户名已存在");
         }
        if(user.getPassword()==null){
            user.setPassword(defaultPassword);
        }
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        int count = userService.registerUser(user);
        if(count==0){
            return R.success("注册失败，请稍后再试") ;
        }
        redisTemplate.opsForValue().set("login"+user.getId(),user);
        String token = jwtTokenUtil.generateToken(new Login(user));
        R data = R.success("注册成功");
        Map<String, String> map = new HashMap<>();
        map.put("token",token);
        data.setData(map);
        return data;
    }

    @GetMapping("/count")
    public R getUserNumber(){
        int i = userService.selectUserNumber();
        HashMap<String,Integer> map=new HashMap<>();
        map.put("data",i);
//        return i;
        return R.success(null,map);
    }

}
