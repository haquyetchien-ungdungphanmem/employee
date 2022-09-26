package com.example.qlnvproject.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class employeeDto {


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
