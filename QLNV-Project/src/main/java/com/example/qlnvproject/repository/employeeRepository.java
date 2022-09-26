package com.example.qlnvproject.repository;

import com.example.qlnvproject.model.Department;
import com.example.qlnvproject.model.Employee;
import com.example.qlnvproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface employeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username);

    List<Employee> getEmployeesByRole(Role role);

    List<Employee> getEmployeesByDepartment(Department department);

    Boolean existsByUsername(String username);
}
