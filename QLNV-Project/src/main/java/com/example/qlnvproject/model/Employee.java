package com.example.qlnvproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String fullname;
    @NotEmpty
    @Email
    private String email;


    @NotEmpty
    @Size(min = 8, message = "password should have at least 8 characters")
    private String pass;

    @NotEmpty

    private String birthday;
    @NotEmpty
    @Size(min = 2, message = "degree should have at least 2 characters")
    private String degree;
    @NotEmpty
    @Size(min = 2, message = "specialize should have at least 2 characters")
    private String specialize;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true, referencedColumnName = "department_id")
    @JsonManagedReference
    private Department department;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false, referencedColumnName = "roleId")
    @JsonManagedReference
    private Role role;





}
