package com.study.springDesign.mapper;

import com.study.springDesign.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    int insertEmployee(Employee employee);
    List<Employee> getAllEmployee();
    int selectEmployeeByUsername(@Param("username") String username);
    int deleteEmployeeById(Integer id);
    int updateStatusById(Integer status,Integer id);
}
