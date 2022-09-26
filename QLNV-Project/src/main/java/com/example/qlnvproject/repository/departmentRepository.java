package com.example.qlnvproject.repository;

import com.example.qlnvproject.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface departmentRepository extends JpaRepository<Department, Long> {
}
