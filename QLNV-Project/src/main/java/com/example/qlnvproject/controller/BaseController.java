package com.example.qlnvproject.controller;

import com.example.qlnvproject.dto.EmployeeUpdateDto;
import com.example.qlnvproject.jwt.JwtFilter;
import com.example.qlnvproject.jwt.JwtUtil;
import com.example.qlnvproject.model.Employee;
import com.example.qlnvproject.service.AccountService;
import com.example.qlnvproject.service.RoleService;
import com.example.qlnvproject.service.departmentService;
import com.example.qlnvproject.service.employeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    public RoleService roleService;

    @Autowired
    public departmentService departmentService;

    @Autowired
    public employeeService employeeService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountService accountService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

   public Employee getActiveAccount(HttpServletRequest httpServletRequest){
       String token = jwtFilter.jwtByRequest(httpServletRequest);
       String usernameByToken = jwtUtil.getUsernameByToken(token);
       return employeeService.findByUsername(usernameByToken);
   }

   public void update(EmployeeUpdateDto employeeUpdateDto, Employee employee){
       BeanUtils.copyProperties(employeeUpdateDto, employee);
       employee.setDepartment(employee.getDepartment());
       employee.setPass(employee.getPass());
       employee.setUsername(employee.getUsername());
       employee.setRole(employee.getRole());
   }

}
