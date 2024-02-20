package com.study.springDesign.service;

import com.study.springDesign.pojo.Employee;

import java.sql.Timestamp;
import java.util.List;

public interface EmployeeService {
    void registerEmployee(Employee employee);
    List<Employee> getEmploy();

    List<Employee> getEmploy(Integer startIndex,Integer pageSize);

    Employee getEmployee(Integer id);
    Employee getEmployeeByUsername(String username);

    int delEmployById(Integer id);

    int changeStatusById(Integer status, Integer id);

    void changeUpdateTimeAndUpdateUser(Integer id, Timestamp updateTime, String updateUser);

    int getEmployeeNumber();
}
