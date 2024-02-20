package com.study.springDesign.controller;

import com.study.springDesign.Util.JwtTokenUtil;
import com.study.springDesign.Util.R;
import com.study.springDesign.annotation.UpdateCommon;
import com.study.springDesign.pojo.Employee;
import com.study.springDesign.service.EmployeeService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class employeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @PostMapping("/add")
    public R register(@RequestBody Employee employee, @RequestHeader("token") String token){

        Employee user = employeeService.getEmployeeByUsername(employee.getUsername());
        if(!Objects.isNull(user))
            return R.error("该用户名已存在");
        else{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            employee.setCreateTime(timestamp);
            employee.setUpdateTime(timestamp);
            employee.setStatus(1);
            Claims claimsFromToken = jwtTokenUtil.getClaimsFromToken(token);
            String username = claimsFromToken.getSubject();
            employee.setCreateUser(username);
            employee.setUpdateUser(username);
            employeeService.registerEmployee(employee);
            return R.success("员工注册成功");
        }
    }
    @GetMapping("/getEmployee")
    public R getEmployee(){
        List<Employee> employee = (List<Employee>) employeeService.getEmploy();
        return R.success("",employee);
    }
    @PostMapping("/getEmployee")
    public R getEmployee(@RequestParam Integer startIndex,@RequestParam Integer pageSize){
        List<Employee> employByPage = employeeService.getEmploy(startIndex, pageSize);
        return R.success("查询成功",employByPage);
    }
    @GetMapping("/count")
    public R countEmployee(){
        int employeeNumber = employeeService.getEmployeeNumber();
        Map<String, Integer> map = new HashMap<>();
        map.put("employeeNumber",employeeNumber);
        return R.success("获取成功",map);
    }

    @DeleteMapping("deleteEmployee")
    public R delEmployee(@RequestParam Integer id){
        int i = employeeService.delEmployById(id);
        if(i==1)return R.success("删除成功");
        else return R.error("删除失败，请稍后再试");
    }
    @PostMapping("/changeStatus")
//    @UpdateCommon(token = "token")
    public R changeStatus(@RequestParam Integer status,@RequestParam Integer id,@RequestParam String token){
        int i = employeeService.changeStatusById(status, id);
        System.out.println(i);
        String userNameFromToken = jwtTokenUtil.getUserNameFromToken(token);
        if(i==1){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            employeeService.changeUpdateTimeAndUpdateUser(id,
                    timestamp,
                    userNameFromToken);
            R result =  R.success("修改成功");
            Map<String, String> map = new HashMap<String, String>();
            map.put("updateUser",userNameFromToken);
            return R.success("修改成功",map);
        }
        else return R.success("修改失败，请稍后再试");
    }

}
