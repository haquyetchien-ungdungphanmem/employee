package com.example.qlnvproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "department")
public class Department {
    @Id
    private long department_id;
    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String departmentName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Employee> employeeList;

}
