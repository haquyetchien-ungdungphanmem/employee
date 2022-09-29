package com.example.qlnvproject.service.impl;

import com.example.qlnvproject.dto.EmployeeUpdateDto;
import com.example.qlnvproject.jwt.JwtFilter;
import com.example.qlnvproject.jwt.JwtUtil;
import com.example.qlnvproject.model.Department;
import com.example.qlnvproject.model.Employee;
import com.example.qlnvproject.model.Role;
import com.example.qlnvproject.repository.RoleRepository;
import com.example.qlnvproject.repository.departmentRepository;
import com.example.qlnvproject.repository.employeeRepository;
import com.example.qlnvproject.service.employeeService;
import org.springframework.beans.BeanUtils;
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

    @Override
    public Employee save(Employee employee) {
        employee.setPass(passwordEncoder.encode(employee.getPass()));
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee
            , Employee employeeLogin, EmployeeUpdateDto employeeUpdateDto) {
        Department department = departmentRepository.findById(employeeUpdateDto.getDepartment_id()).get();
        if (employeeLogin.getRole().getRoleId() == 1) {
            BeanUtils.copyProperties(employeeUpdateDto, employee);
            employee.setDepartment(department);
            employee.setPass(employee.getPass());
            employee.setUsername(employee.getUsername());
            employee.setRole(employee.getRole());
            return employeeRepository.save(employee);
        } else if (employeeLogin.getRole().getRoleId() == 2
                && employee.getDepartment() != null
                && employeeLogin.getDepartment().getDepartment_id() == employee.getDepartment().getDepartment_id()
        ) {
            BeanUtils.copyProperties(employeeUpdateDto, employee);
            employee.setDepartment(employee.getDepartment());
            employee.setPass(employee.getPass());
            employee.setUsername(employee.getUsername());
            employee.setRole(employee.getRole());
            return employeeRepository.save(employee);
        } else if (employeeLogin.getRole().getRoleId() == 3 && employee.getRole().getRoleId() == 4) {
            BeanUtils.copyProperties(employeeUpdateDto, employee);
            employee.setDepartment(employee.getDepartment());
            employee.setPass(employee.getPass());
            employee.setUsername(employee.getUsername());
            employee.setRole(employee.getRole());
            return employeeRepository.save(employee);
        } else if (employeeLogin.getRole().getRoleId() == 4 && employeeLogin.getUsername().equals(employee.getUsername())) {
            BeanUtils.copyProperties(employeeUpdateDto, employee);
            employee.setDepartment(employee.getDepartment());
            employee.setPass(employee.getPass());
            employee.setUsername(employee.getUsername());
            employee.setRole(employee.getRole());
            return employeeRepository.save(employee);
        } else {
            return null;
        }
    }

    @Override
    public Employee findEmployeeById(Employee employeeFind, Employee employeeLogin) {
        if (employeeLogin.getRole().getRoleId() == 1) {
            return employeeRepository.findById(employeeFind.getId()).get();
        } else if (employeeLogin.getRole().getRoleId() == 2
                && employeeFind.getDepartment() != null
                && employeeLogin.getDepartment().getDepartment_id() == employeeFind.getDepartment().getDepartment_id()
        ) {
            return employeeRepository.findById(employeeFind.getId()).get();
        } else if (employeeLogin.getRole().getRoleId() == 3
                && employeeFind.getRole().getRoleId() == 4) {
            return employeeRepository.findById(employeeFind.getId()).get();
        } else if (employeeLogin.getRole().getRoleId() == 4
                && employeeLogin.getId() == employeeFind.getId()) {
            return employeeRepository.findById(employeeFind.getId()).get();
        } else {
            return null;
        }
    }

}
