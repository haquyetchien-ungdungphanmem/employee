package com.example.qlnvproject.service.impl;

import com.example.qlnvproject.model.Department;
import com.example.qlnvproject.repository.departmentRepository;
import com.example.qlnvproject.service.departmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class departmentServiceImpl implements departmentService {
    @Autowired
    private departmentRepository departmentRepository;
    public departmentServiceImpl(departmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department insertDepartment(Department department){
        departmentRepository.save(department);

        return department;
    }

    @Override
    public Department getDepartmentById(long id){
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(long id, Department departmentInput){
        Department department = departmentRepository.findById(id).orElse(null);
//        department.setDepartment_id(departmentInput.getDepartment_id());
        department.setDepartmentName(departmentInput.getDepartmentName());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartmentById(long id){
        Department department = departmentRepository.findById(id).orElse(null);
        assert department != null;
        departmentRepository.deleteById(department.getDepartment_id());
    }

    @Override
    public Department save(Department dpmRequest) {
        return departmentRepository.save(dpmRequest);
    }


}
