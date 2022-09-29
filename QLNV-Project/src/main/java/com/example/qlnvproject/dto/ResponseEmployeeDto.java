package com.example.qlnvproject.dto;

import com.example.qlnvproject.model.Department;
import com.example.qlnvproject.model.Role;
import lombok.Data;

@Data
public class ResponseEmployeeDto {

    private long id;

    private String username;

    private String fullname;

    private String email;

    private String birthday;

    private Role role;

    private Department department;

    private String degree;

    private String specialize;
}
