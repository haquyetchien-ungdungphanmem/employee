package com.example.qlnvproject.controller;

import com.example.qlnvproject.dto.EmployeeUpdateDto;
import com.example.qlnvproject.dto.ResponseEmployeeDto;
import com.example.qlnvproject.dto.employeeCreateDto;
import com.example.qlnvproject.jwt.JwtFilter;
import com.example.qlnvproject.jwt.JwtUtil;
import com.example.qlnvproject.model.Employee;
import com.example.qlnvproject.service.employeeService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("employee")
public class employeeController {

    @Autowired
    public employeeService employeeService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;


    private ModelMapper modelMapper;

    public employeeController(employeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/insert")

    public ResponseEntity<ResponseEmployeeDto> insertEmployee(@RequestBody employeeCreateDto empDTO) {
        if (employeeService.findByUsername(empDTO.getUsername()) == null) {
            Employee empRequest = modelMapper.map(empDTO, Employee.class);
            Employee emp = employeeService.save(empRequest);
            ResponseEmployeeDto empResponse = modelMapper.map(emp, ResponseEmployeeDto.class);
            return new ResponseEntity<ResponseEmployeeDto>(empResponse, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById")
    public ResponseEntity<ResponseEmployeeDto> getEmployeeById(@RequestParam("id") long id, HttpServletRequest request) {

        Employee emp = employeeService.getEmployeeById(id);
        Employee employeeLogin = getActiveAccount(request);
        Employee employeeRespon = employeeService.findEmployeeById(emp, employeeLogin);
        if (employeeRespon != null) {
            ResponseEmployeeDto empResponse = modelMapper.map(emp, ResponseEmployeeDto.class);
            return ResponseEntity.ok().body(empResponse);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getAll")

    public List<ResponseEmployeeDto> getAllEmployee() {
        return employeeService.getAllEmployee().stream().map(employee -> modelMapper.map(employee, ResponseEmployeeDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getEmployeeByRoleNhanVien")

    public List<ResponseEmployeeDto> getEmployeeByRoleNhanVien() {
        return employeeService.getEmployeeByRoleNhanVien(4).stream().map(employee
                -> modelMapper.map(employee, ResponseEmployeeDto.class)).collect(Collectors.toList());

    }

    @GetMapping("/getEmployeeByDepartmentId")

    public List<ResponseEmployeeDto> getEmployeeByDepartmentId(@RequestParam("id") long id) {
        return employeeService.getEmployeeByDepartmentId(id).stream().map(employee
                -> modelMapper.map(employee, ResponseEmployeeDto.class)).collect(Collectors.toList());
    }


    @PostMapping("/delete")

    public ResponseEntity<?> deleteEmployeeById(@RequestParam("id") long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Delete success!");
    }

    @PostMapping("/deteleInDepartment")
    
    public ResponseEntity<?> deleteInDepartment(@RequestParam("id") long id) {
        employeeService.deleteInDepartmnetById(id);
        return ResponseEntity.ok("Delete success!");
    }

    @PostMapping("/updateEmployeeActive")
    public ResponseEmployeeDto updateEmployeeActive(
            @RequestBody EmployeeUpdateDto employeeUpdateDto, HttpServletRequest httpServletRequest) {
        Employee employee = getActiveAccount(httpServletRequest);
        update(employeeUpdateDto, employee);
        Employee employeeUpdate = employeeService.save(employee);
        ResponseEmployeeDto responseEmployeeDto = modelMapper.map(employeeUpdate, ResponseEmployeeDto.class);
        return responseEmployeeDto;
    }

    @GetMapping("/getEmployeeActive")
    public ResponseEmployeeDto getEmployeeActive(HttpServletRequest request) {
        Employee employee = getActiveAccount(request);
        ResponseEmployeeDto responseEmployeeDto = modelMapper.map(employee, ResponseEmployeeDto.class);
        return responseEmployeeDto;
    }

    @PostMapping("/updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestParam("id") long id
            , @RequestBody EmployeeUpdateDto employeeUpdateDto, HttpServletRequest request) throws Exception {
        Employee employeeLogin = getActiveAccount(request);
        Employee employeeUpdate = employeeService.getEmployeeById(id);
        Employee employeeRespon = employeeService.updateEmployee(employeeUpdate, employeeLogin, employeeUpdateDto);
        if (employeeRespon != null) {
            ResponseEmployeeDto responseEmployeeDto = modelMapper.map(employeeRespon, ResponseEmployeeDto.class);
            return ResponseEntity.ok(responseEmployeeDto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public Employee getActiveAccount(HttpServletRequest httpServletRequest) {
        String token = jwtFilter.jwtByRequest(httpServletRequest);
        String usernameByToken = jwtUtil.getUsernameByToken(token);
        return employeeService.findByUsername(usernameByToken);
    }

    public void update(EmployeeUpdateDto employeeUpdateDto, Employee employee) {
        BeanUtils.copyProperties(employeeUpdateDto, employee);
        employee.setDepartment(employee.getDepartment());
        employee.setPass(employee.getPass());
        employee.setUsername(employee.getUsername());
        employee.setRole(employee.getRole());
    }
}
