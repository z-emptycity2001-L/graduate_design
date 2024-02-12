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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    @Value("${token.expiration}")
    private String expiration;
    @PostMapping("/register")
    public R register(@RequestBody User user){
        //判断该用户是否存在
         if(!Objects.isNull(userService.searchUserByUsername(user.getUsername()))){
             return R.error("注册失败，该用户名已存在");
         }
         //若用户没有设置密码，则自动设置默认密码
        if(user.getPassword()==null){
            user.setPassword(defaultPassword);
        }
        //设置用户创建时间和更新时间
        Date date = new Date(System.currentTimeMillis());
        user.setCreateTime(date);
        user.setUpdateTime(date);
        //进行密码加密处理
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
//        进行用户注册
        int count = userService.registerUser(user);
        if(count==0){
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
        User userByUsername = userService.searchUserByUsername(user.getUsername());
        if(userByUsername==null) return R.error("用户名或密码错误");
        boolean matches = passwordEncoder.matches(user.getPassword(), userByUsername.getPassword());
        if(!matches){
            return R.error("用户名或密码错误");
        }else{
            //在redis中缓存用户信息
            redisTemplate.opsForValue().set("login"+user.getUsername(),user, Long.parseLong(expiration), TimeUnit.MILLISECONDS);
//        生成token凭证
            String token = jwtTokenUtil.generateToken(new Login(user));
//        返回执行结果
            R data = R.success("登录成功");
            Map<String, String> map = new HashMap<>();
            map.put("token",token);
            data.setData(map);
            return data;
        }
    }

}
