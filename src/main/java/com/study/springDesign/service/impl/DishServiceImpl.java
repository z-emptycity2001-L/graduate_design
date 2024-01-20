package com.study.springDesign.service.impl;

import com.study.springDesign.mapper.DishMapper;
import com.study.springDesign.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Override
    public List<Map<String, Object>> getAllDish() {
        return dishMapper.selectAllDishesStep1();
    }
}
