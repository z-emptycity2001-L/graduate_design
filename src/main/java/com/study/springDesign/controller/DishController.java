package com.study.springDesign.controller;

import com.study.springDesign.Util.R;
import com.study.springDesign.pojo.Dish;
import com.study.springDesign.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class DishController {
    @Autowired
    private DishService dishService;
    @GetMapping("/dish")
    public List<Map<String,Object>> getAllDish(){
        dishService.getAllDish().forEach(dish -> System.out.println("spring"+dish));
//        System.out.println(dishService.getAllDish().toString());
        return dishService.getAllDish();
    }

    @GetMapping("/getDish")
    public R getDishController(){
        Dish dish = dishService.getDish();
        return R.success("获取成功",dish);
    }
}
