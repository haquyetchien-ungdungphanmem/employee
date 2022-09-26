package com.example.qlnvproject.service;

import com.example.qlnvproject.model.Employee;

import java.util.List;

public interface employeeService {
    Employee insertEmployee(Employee emp);
    Employee getEmployeeById(long id);
    List<Employee> getAllEmployee();
    Employee updateEmployee(long id, Employee emp);
    void deleteEmployeeById(long id);

    void deleteInDepartmnetById(long id);

    List<Employee> getEmployeeByRoleNhanVien(long role);

    List<Employee> getEmployeeByDepartmentId(long id);

    Employee findByUsername(String username);
}
