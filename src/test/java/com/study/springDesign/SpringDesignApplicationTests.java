package com.study.springDesign;

import com.study.springDesign.mapper.UserMapper;
import com.study.springDesign.pojo.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan(basePackages = "com.study.springDesign.mapper")
class SpringDesignApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
//        int count = userMapper.insertUser(new User(null, "泡影", "12342345643", "男", "diefoew", "江西省", "empty_city",null,null));
        int i = userMapper.countUser();
        System.out.println(i);
//        System.out.println(count);
    }

}
