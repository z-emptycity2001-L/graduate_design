package com.study.springDesign;

import com.study.springDesign.mapper.DishMapper;
import com.study.springDesign.mapper.UserMapper;
import com.study.springDesign.pojo.Dish;
import com.study.springDesign.pojo.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
@MapperScan(basePackages = "com.study.springDesign.mapper")
class SpringDesignApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DishMapper dishMapper;
    @Test
    void contextLoads() {
    }

}
