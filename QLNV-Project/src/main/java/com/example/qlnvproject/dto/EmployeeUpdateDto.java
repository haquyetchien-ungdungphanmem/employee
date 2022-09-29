package com.example.qlnvproject.dto;


import lombok.Data;

@Data
public class EmployeeUpdateDto {



    private String fullname;

    private String email;

    private String birthday;

    private long department_id;

    private String degree;

    private String specialize;
}
