package com.study.springDesign.controller;

import com.study.springDesign.Util.JwtTokenUtil;
import com.study.springDesign.Util.R;
import com.study.springDesign.pojo.Employee;
import com.study.springDesign.service.EmployeeService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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

        int usernameNum = employeeService.getEmployeeByUsername(employee.getUsername());
        if(usernameNum==1)
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

    @DeleteMapping("deleteEmployee")
    public R delEmployee(@RequestParam Integer id){
        int i = employeeService.delEmployById(id);
        if(i==1)return R.success("删除成功");
        else return R.error("删除失败，请稍后再试");
    }
    @PostMapping("changeStatus")
    public R changeStatus(@RequestParam Integer status,@RequestParam Integer id){
        int i = employeeService.changeStatusById(status, id);
        if(i==1)return R.success("修改成功");
        else return R.success("修改失败，请稍后再试");
    }
}
