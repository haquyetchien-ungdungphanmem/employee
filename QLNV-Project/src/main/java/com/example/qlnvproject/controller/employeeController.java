package com.example.qlnvproject.controller;

import com.example.qlnvproject.dto.EmployeeUpdateDto;
import com.example.qlnvproject.dto.ResponseEmployeeDto;
import com.example.qlnvproject.dto.employeeCreateDto;
import com.example.qlnvproject.model.Employee;
import com.example.qlnvproject.service.employeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("employee")
public class employeeController extends BaseController {
    private ModelMapper modelMapper;

    public employeeController(employeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/insert")
    @PreAuthorize("hasRole('giamdoc')")
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
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('giamdoc')")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee().stream().map(employee -> modelMapper.map(employee, Employee.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getEmployeeByRoleNhanVien")
    @PreAuthorize("hasAnyRole('giamdoc', 'nhanviennhansu')")
    public List<ResponseEmployeeDto> getEmployeeByRoleNhanVien() {
        return employeeService.getEmployeeByRoleNhanVien(4).stream().map(employee
                -> modelMapper.map(employee, ResponseEmployeeDto.class)).collect(Collectors.toList());

    }

    @GetMapping("/getEmployeeByDepartmentId")
    @PreAuthorize("hasAnyRole('giamdoc','truongphong')")
    public List<ResponseEmployeeDto> getEmployeeByDepartmentId(@RequestParam("id") long id) {
        return employeeService.getEmployeeByDepartmentId(id).stream().map(employee
                -> modelMapper.map(employee, ResponseEmployeeDto.class)).collect(Collectors.toList());
    }


    @PostMapping("/delete")
    @PreAuthorize("hasRole('giamdoc')")
    public ResponseEntity<?> deleteEmployeeById(@RequestParam("id") long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Delete success!");
    }

    @PostMapping("/deteleInDepartment")
    @PreAuthorize("hasAnyRole('truongphong', 'giamdoc')")
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
}
