package com.example.qlnvproject.dto;

import lombok.Data;

@Data
public class employeeCreateDto {


    private String username;

    private String fullname;

    private String email;

    private String pass;

    private String birthday;

    private long roleId;

    private long department_id;

    private String degree;

    private String specialize;
}
