package com.example.qlnvproject.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class employeeCreateDto {

    @Size(min = 2, message = "user name should have at least 2 characters")
    private String username;
    @Size(min = 2, message = "full name should have at least 2 characters")
    private String fullname;
    @Email
    private String email;
    @Size(min = 8, message = "password should have at least 8 characters")
    private String pass;

    private String birthday;

    private long roleId;

    private long department_id;
    @Size(min = 2, message = "degree should have at least 2 characters")
    private String degree;
    @Size(min = 2, message = "specialize should have at least 2 characters")
    private String specialize;
}
