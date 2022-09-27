package com.example.qlnvproject.controller;

import com.example.qlnvproject.dto.DepartmentUpdateDto;
import com.example.qlnvproject.dto.departmentDto;
import com.example.qlnvproject.model.Department;
import com.example.qlnvproject.service.departmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/department")
public class departmentController extends BaseController{

    private final ModelMapper modelMapper;

    public departmentController(departmentService departmentService, ModelMapper modelMapper){
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/insert")
    public ResponseEntity<departmentDto> insertDepartment(@RequestBody departmentDto dpmDTO){
    //Convert entity to dto
    Department dpmRequest= modelMapper.map(dpmDTO, Department.class);
    Department dpm = departmentService.insertDepartment(dpmRequest);
    //Convert dto to entity
    departmentDto dpmResponse = modelMapper.map(dpm, departmentDto.class);
    return new ResponseEntity<departmentDto>(dpmResponse, HttpStatus.CREATED);


    }

    @GetMapping("/getById")
    public ResponseEntity<departmentDto> getDepartmentById(@RequestParam("id") long id){
        Department department = departmentService.getDepartmentById(id);
        departmentDto dpmResponse =modelMapper.map(department, departmentDto.class);
        return ResponseEntity.ok().body(dpmResponse);

    }

    @GetMapping("/getAll")
    public List<departmentDto> getAllDepartment(){
        return departmentService.getAllDepartment().stream().map(department -> modelMapper.map(department,
                departmentDto.class)).collect(Collectors.toList());
    }

    @PostMapping("/update")
    public ResponseEntity<DepartmentUpdateDto> updateDepartment(@RequestParam("id") long id, @RequestBody DepartmentUpdateDto dpmDTO){
        Department department = departmentService.getDepartmentById(id);
        BeanUtils.copyProperties(dpmDTO, department);
        department.setDepartment_id(department.getDepartment_id());
        Department dpm = departmentService.save(department);
        DepartmentUpdateDto dpmResponse = modelMapper.map(dpm,DepartmentUpdateDto.class );
        return ResponseEntity.ok().body(dpmResponse);

    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteDepartmentById(@RequestParam("id") long id){
        departmentService.deleteDepartmentById(id);
        return ResponseEntity.noContent().build();
    }

}
