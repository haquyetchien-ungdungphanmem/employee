package com.example.qlnvproject.controller;


import com.example.qlnvproject.dto.RoleDto;
import com.example.qlnvproject.model.Role;
import com.example.qlnvproject.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    public RoleService roleService;

    public RoleController(RoleService roleService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RoleDto roleDto, Role role) {
        BeanUtils.copyProperties(roleDto, role);
        if ( roleService.save(role) != null) {
             roleService.save(role);
            return ResponseEntity.ok().body(role);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get")
    public List<RoleDto> getAll() {
        return roleService.getAll().stream().map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestParam("id") long id) {
        roleService.deleteByRoleId(id);
        return ResponseEntity.ok("Delete success!");
    }

    @GetMapping("/findByRoleName")
    public Role findByRoleName(@RequestParam("roleName") String roleName) {
        return roleService.findByRole(roleName);
    }
}
