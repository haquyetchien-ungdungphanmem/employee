package com.example.qlnvproject.controller;


import com.example.qlnvproject.dto.RoleDto;
import com.example.qlnvproject.dto.employeeDto;
import com.example.qlnvproject.model.Employee;
import com.example.qlnvproject.model.Role;
import com.example.qlnvproject.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController{

    public RoleController(RoleService roleService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<RoleDto> create(@RequestBody RoleDto roleDto){

        Role roleRequest = modelMapper.map(roleDto, Role.class);
        Role role = roleService.save(roleRequest);
        RoleDto roleResponse = modelMapper.map(role, RoleDto.class);

        return new ResponseEntity<RoleDto>(roleResponse, HttpStatus.CREATED);
    }
}
