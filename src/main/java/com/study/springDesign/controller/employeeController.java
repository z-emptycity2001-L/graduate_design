package com.study.springDesign.controller;

import com.study.springDesign.Util.JwtTokenUtil;
import com.study.springDesign.Util.R;
import com.study.springDesign.pojo.Employee;
import com.study.springDesign.service.EmployeeService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Date date = new Date(System.currentTimeMillis());
        employee.setCreateTime(date);
        employee.setUpdateTime(date);
        employee.setStatus("1");
        Claims claimsFromToken = jwtTokenUtil.getClaimsFromToken(token);
        String username = claimsFromToken.getSubject();
        employee.setCreateUser(username);
        employee.setUpdateUser(username);
        employeeService.registerEmployee(employee);

        return R.success("员工注册成功");
    }

    @GetMapping("/getEmployee")
    public R getEmploy(){
        List<Employee> employee = (List<Employee>) employeeService.getEmploy();
        return R.success("",employee);
    }
}
