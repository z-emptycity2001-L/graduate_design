package com.study.springDesign.service;

import com.study.springDesign.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    void registerEmployee(Employee employee);
    List<Employee> getEmploy();
}
