package com.study.springDesign.service.impl;

import com.study.springDesign.mapper.DishMapper;
import com.study.springDesign.pojo.Dish;
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

    @Override
    public List<Map<String, Object>> getDish(int pageIndex, int pageSize) {
        return dishMapper.selectDishByPage(pageIndex,pageSize);
    }

    @Override
    public List<Dish> getDish() {
        return  dishMapper.selectAllDish();
    }


    @Override
    public int getDishNumber() {
        return dishMapper.selectDishNumber();
    }
    @Override
    public int deleteDish(Long id) {
        return dishMapper.deleteDishById(id);
    }

    @Override
    public int changeDishStatus(int status,Long id) {
        return dishMapper.updateDishStatusById(status,id);
    }
}
