package com.study.springDesign.service;

import com.study.springDesign.pojo.Dish;

import java.util.List;
import java.util.Map;

public interface DishService {
    List<Map<String,Object>> getAllDish();
}
