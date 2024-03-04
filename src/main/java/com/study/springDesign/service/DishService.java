package com.study.springDesign.service;

import com.study.springDesign.pojo.Dish;

import java.util.List;
import java.util.Map;

public interface DishService {
    List<Map<String,Object>> getAllDish();
    List<Dish> getDish();

    int getDishNumber();

//    分页查询菜品
    List<Map<String,Object>> getDish(int pageIndex,int pageSize);

    int deleteDish(Long id);

    int changeDishStatus(int status,Long id);
}
