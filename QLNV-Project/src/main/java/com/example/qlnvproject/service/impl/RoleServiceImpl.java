package com.example.qlnvproject.service.impl;

import com.example.qlnvproject.dto.RoleDto;
import com.example.qlnvproject.model.Role;
import com.example.qlnvproject.repository.RoleRepository;
import com.example.qlnvproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role roleRequest) {
        if (findByRole(roleRequest.getRoleName()) == null && findById(roleRequest.getRoleId()) == null){
            roleRepository.save(roleRequest);
            return roleRequest;
        }else {
            return null;
        }
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteByRoleId(long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findById(long roleId) {
        if (roleRepository.findByRoleId(roleId) == null){
            return null;
        }else {
            return roleRepository.findByRoleId(roleId);
        }

    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRoleName(role);
    }
}
