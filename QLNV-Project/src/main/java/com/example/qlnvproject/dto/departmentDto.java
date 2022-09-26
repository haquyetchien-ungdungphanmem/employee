package com.example.qlnvproject.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class departmentDto {
    private long department_id;

    @NotEmpty
    private String departmentName;

}
