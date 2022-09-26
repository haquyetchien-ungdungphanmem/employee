package com.example.qlnvproject.service.impl;

import com.example.qlnvproject.jwt.JwtFilter;
import com.example.qlnvproject.jwt.JwtUtil;
import com.example.qlnvproject.model.Department;
import com.example.qlnvproject.model.Employee;
import com.example.qlnvproject.model.Role;
import com.example.qlnvproject.repository.RoleRepository;
import com.example.qlnvproject.repository.departmentRepository;
import com.example.qlnvproject.repository.employeeRepository;
import com.example.qlnvproject.service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class employeeServiceImpl implements employeeService {


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    departmentRepository departmentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private employeeRepository employeeRepository;

    public employeeServiceImpl(employeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        employee.setPass(passwordEncoder.encode(employee.getPass()));
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(long id, Employee employeeInput) {
        Employee emp = employeeRepository.findById(id).orElse(null);
        emp.setFullname(employeeInput.getFullname());
        emp.setEmail(employeeInput.getEmail());
        emp.setBirthday(employeeInput.getBirthday());
        emp.setDegree(employeeInput.getDegree());
        emp.setSpecialize(employeeInput.getSpecialize());
        return employeeRepository.save(employeeInput);
    }

    @Override
    public void deleteEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        assert employee != null;
        employeeRepository.deleteById(employee.getId());
    }

    @Override
    public void deleteInDepartmnetById(long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        assert employee != null;
        employee.setDepartment(null);
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeeByRoleNhanVien(long id) {
        Role role = roleRepository.findById(id).orElse(null);
        assert role != null;
        return employeeRepository.getEmployeesByRole(role);
    }

    @Override
    public List<Employee> getEmployeeByDepartmentId(long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        assert department != null;
        return employeeRepository.getEmployeesByDepartment(department);
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

}
