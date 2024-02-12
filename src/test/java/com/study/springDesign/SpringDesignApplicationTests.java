package com.study.springDesign;

import com.study.springDesign.Util.JwtTokenUtil;
import com.study.springDesign.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;

@SpringBootTest
class SpringDesignApplicationTests {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void test(){
        String userNameFromToken = jwtTokenUtil.getUserNameFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiLmmK3pmLMiLCJjcmVhdGVkIjoxNzA3NzM0MTEyMzgwLCJleHAiOjE3OTQxMzQxMTJ9.TutNEH8hn2KNBuxKEoy-sSiX37OzVPgXkKq973X6gLg1CdTo1KDxzQfHqbQOpSGWtnZAeQkZR2bFoayh47VNnw");
        User user = (User) redisTemplate.opsForValue().get("login昭阳");
        System.out.println(user);
        System.out.println(userNameFromToken);
    }
}
