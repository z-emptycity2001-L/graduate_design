package com.study.springDesign;

import com.study.springDesign.mapper.UserMapper;
import com.study.springDesign.pojo.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@MapperScan(basePackages = "com.study.springDesign.mapper")
class SpringDesignApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
//        int count = userMapper.insertUser(new User(null, "泡影", "12342345643", "男", "diefoew", "江西省", "empty_city",null,null));
        int i = userMapper.countUser();
        String encode = passwordEncoder.encode("123");
        System.out.println("encode = " + encode);
        System.out.println(passwordEncoder.matches("123", encode));
//        System.out.println(count);
    }

}
