package com.example.qlnvproject.service;

import com.example.qlnvproject.model.Department;

import java.util.List;

public interface departmentService {
    Department insertDepartment(Department dpm);
    Department getDepartmentById(long id);
    List<Department> getAllDepartment();
    Department updateDepartment(long id, Department dpm);
    void deleteDepartmentById(long id);

    Department save(Department dpmRequest);
}
