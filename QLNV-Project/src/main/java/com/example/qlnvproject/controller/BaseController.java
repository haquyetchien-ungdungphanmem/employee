package com.example.qlnvproject.controller;

import com.example.qlnvproject.jwt.JwtUtil;
import com.example.qlnvproject.service.AccountService;
import com.example.qlnvproject.service.RoleService;
import com.example.qlnvproject.service.departmentService;
import com.example.qlnvproject.service.employeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

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

    private static final String giamDoc = "giamdoc";
    private static final String truongPhong = "truongphong";
    private static final String nhanVienNhanSu = "nhanviennhansu";
    private static final String nhanVien = "nhanvien";

}
