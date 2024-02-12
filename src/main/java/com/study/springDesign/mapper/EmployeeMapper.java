package com.study.springDesign.mapper;

import com.study.springDesign.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    int insertEmployee(Employee employee);
    List<Employee> getAllEmployee();
}
