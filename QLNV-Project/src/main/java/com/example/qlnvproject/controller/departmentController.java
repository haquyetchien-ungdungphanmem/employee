package com.example.qlnvproject.controller;

import com.example.qlnvproject.dto.departmentDto;
import com.example.qlnvproject.model.Department;
import com.example.qlnvproject.service.departmentService;
import org.modelmapper.ModelMapper;
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

    @GetMapping("/getById/{id}")
    public ResponseEntity<departmentDto> getDepartmentById(@PathVariable long id){
        Department department = departmentService.getDepartmentById(id);
        departmentDto dpmResponse =modelMapper.map(department, departmentDto.class);
        return ResponseEntity.ok().body(dpmResponse);

    }

    @GetMapping("/getAll")
    public List<departmentDto> getAllDepartment(){
        return departmentService.getAllDepartment().stream().map(department -> modelMapper.map(department, departmentDto.class)).collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<departmentDto> updateDepartment(@PathVariable long id, @RequestBody departmentDto dpmDTO){
        Department dpmRequest = modelMapper.map(dpmDTO, Department.class);
        Department dpm = departmentService.updateDepartment(id, dpmRequest);
        departmentDto dpmResponse = modelMapper.map(dpm,departmentDto.class );
        return ResponseEntity.ok().body(dpmResponse);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable long id){
        departmentService.deleteDepartmentById(id);
        return ResponseEntity.noContent().build();
    }

}
