package com.study.springDesign.mapper;

import com.study.springDesign.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface EmployeeMapper {
    int insertEmployee(Employee employee);
    List<Employee> getAllEmployee();
    Employee selectEmployeeByUsername(String username);

    Employee selectEmployeeById(Integer id);
    int deleteEmployeeById(Integer id);
    int updateStatusById(Integer status,Integer id);

    List<Employee> selectEmployeeByPage(Integer startIndex, Integer pageSize);

    int updateEmployeeUpdateTimeAndUpdateUser(Integer id, Timestamp updateTime,String updateUser);

    int getAllEmployeeNumber();
}
