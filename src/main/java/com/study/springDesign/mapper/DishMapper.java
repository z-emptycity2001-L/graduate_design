package com.study.springDesign.mapper;

import com.study.springDesign.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {
    List<Map<String,Object>> selectAllDishesStep1();
    Dish selectAllDish();
}