package com.study.springDesign.service.impl;

import com.study.springDesign.mapper.EmployeeMapper;
import com.study.springDesign.pojo.Employee;
import com.study.springDesign.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public void registerEmployee(Employee employee) {
         employeeMapper.insertEmployee(employee);
    }

    @Override
    public List<Employee> getEmploy() {
        return employeeMapper.getAllEmployee();
    }

    @Override
    public int getEmployeeByUsername(String username) {
        return employeeMapper.selectEmployeeByUsername(username);
    }

    @Override
    public int delEmployById(Integer id) {
       return employeeMapper.deleteEmployeeById(id);
    }
    @Override
    public int changeStatusById(Integer status,Integer id){
        return employeeMapper.updateStatusById(status,id);
    }
}
