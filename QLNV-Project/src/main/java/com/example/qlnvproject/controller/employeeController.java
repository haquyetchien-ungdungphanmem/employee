package com.example.qlnvproject.controller;

import com.example.qlnvproject.dto.ResponseEmployeeDto;
import com.example.qlnvproject.dto.employeeDto;
import com.example.qlnvproject.model.Employee;
import com.example.qlnvproject.service.employeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("employee")
public class employeeController extends BaseController{
    private ModelMapper modelMapper;

    public employeeController(employeeService employeeService, ModelMapper modelMapper){
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/insert")
    @PreAuthorize("hasRole('giamdoc')")
    public ResponseEntity<ResponseEmployeeDto> insertEmployee(@RequestBody employeeDto empDTO){
        if (employeeService.findByUsername(empDTO.getUsername()) == null) {
            Employee empRequest = modelMapper.map(empDTO, Employee.class);
            Employee emp = employeeService.insertEmployee(empRequest);
            ResponseEmployeeDto empResponse = modelMapper.map(emp, ResponseEmployeeDto.class);
            return new ResponseEntity<ResponseEmployeeDto>(empResponse, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<ResponseEmployeeDto> getEmployeeById(@RequestParam("id") long id){
        Employee emp = employeeService.getEmployeeById(id);
        ResponseEmployeeDto empResponse =modelMapper.map(emp, ResponseEmployeeDto.class);
        return ResponseEntity.ok().body(empResponse);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('giamdoc')")
    public List<ResponseEmployeeDto> getAllEmployee(){
        return employeeService.getAllEmployee().stream().map(employee -> modelMapper.map(employee, ResponseEmployeeDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getEmployeeByRoleNhanVien")
    @PreAuthorize("hasAnyRole('giamdoc', 'nhanviennhansu')")
    public List<ResponseEmployeeDto> getEmployeeByRoleNhanVien(){
        return employeeService.getEmployeeByRoleNhanVien(4).stream().map(employee
                -> modelMapper.map(employee, ResponseEmployeeDto.class)).collect(Collectors.toList());

    }

    @GetMapping("/getEmployeeByDepartmentId")
    @PreAuthorize("hasAnyRole('giamdoc','truongphong')")
    public List<ResponseEmployeeDto> getEmployeeByDepartmentId(@RequestParam("id") long id){
        return employeeService.getEmployeeByDepartmentId(id).stream().map(employee
                -> modelMapper.map(employee, ResponseEmployeeDto.class)).collect(Collectors.toList());
    }

    @PostMapping("/update")
    public ResponseEntity<employeeDto> updateEmployeeById(@RequestParam("id") long id,  @RequestBody employeeDto empDTO){
        Employee empRequest = modelMapper.map(empDTO, Employee.class);
        Employee emp = employeeService.updateEmployee(id, empRequest);
        employeeDto empResponse = modelMapper.map(emp, employeeDto.class);

        return ResponseEntity.ok().body(empResponse);

    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('giamdoc')")
    public ResponseEntity<?> deleteEmployeeById(@RequestParam("id") long id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Delete success!");
    }

    @PostMapping("/deteleInDepartment")
    @PreAuthorize("hasAnyRole('truongphong', 'giamdoc')")
    public ResponseEntity<?> deleteInDepartment(@RequestParam("id") long id){
        employeeService.deleteInDepartmnetById(id);
        return ResponseEntity.ok("Xóa nhân viên khỏi phòng ban thành công");
    }





}
