package com.study.springDesign.controller;

import com.study.springDesign.Util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/wechat")
public class wechatController {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @PostMapping("/login/{token}/{expires}")
    public R getAccessToken(@PathVariable String token, @PathVariable String expires){
        redisTemplate.opsForValue().set("wechatToken",token, Long.parseLong(expires), TimeUnit.SECONDS);
        return R.success("登陆成功");
    }
}
