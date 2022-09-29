package com.example.qlnvproject.service;

import com.example.qlnvproject.dto.EmployeeUpdateDto;
import com.example.qlnvproject.model.Department;
import com.example.qlnvproject.model.Employee;

import java.util.List;

public interface employeeService {
    Employee insertEmployee(Employee emp);
    Employee getEmployeeById(long id);
    List<Employee> getAllEmployee();

    void deleteEmployeeById(long id);

    void deleteInDepartmnetById(long id);

    List<Employee> getEmployeeByRoleNhanVien(long role);

    List<Employee> getEmployeeByDepartmentId(long id);

    Employee findByUsername(String username);


    Employee save(Employee empRequest);

    Employee updateEmployee(Employee employee, Employee employeeLogin, EmployeeUpdateDto employeeUpdateDto);

    Employee findEmployeeById( Employee employeeFind, Employee employeeLogin);
}
