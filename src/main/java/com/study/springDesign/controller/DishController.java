package com.study.springDesign.controller;

import com.study.springDesign.Util.R;
import com.study.springDesign.pojo.Dish;
import com.study.springDesign.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @GetMapping("/getAllDish")
    public R getDishController(){
        List<Dish> dish = dishService.getDish();
        Map<String, List<Dish>> map = new HashMap<String, List<Dish>>();
        map.put("dish",dish);
        return R.success("获取成功",map);
    }

    @GetMapping("/count")
    public R getDishNumberController(){
        int dishNumber = dishService.getDishNumber();
        Map<String, Integer> map = new HashMap<>();
        map.put("count",dishNumber);
        return R.success(null,map);
    }

    @PostMapping("/pageDish")
    public R getDishByPageController(@RequestParam int pageIndex,@RequestParam int pageSize){
        List<Map<String, Object>> dish = dishService.getDish(pageIndex, pageSize);
        return R.success(null,dish);
    }

    @DeleteMapping("delDish")
    public R deleteDishController(@RequestParam Long id){
        int column = dishService.deleteDish(id);
        if(column>=1)return R.success("删除成功");
        else return R.error("删除失败，请稍后再试");
    }

    @PostMapping("updateDishStatus")
    public R changeDishStatusController(@RequestParam int status ,@RequestParam Long id){
        int column = dishService.changeDishStatus(status,id);
        if(column>=1)return R.success("修改成功");
        else return R.error("修改失败，请稍后再试");
    }
}
