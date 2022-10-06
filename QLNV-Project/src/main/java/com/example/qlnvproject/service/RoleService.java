package com.example.qlnvproject.service;

import com.example.qlnvproject.dto.RoleDto;
import com.example.qlnvproject.model.Role;

import java.util.List;

public interface RoleService {

    Role save(Role roleRequest);

    List<Role> getAll();

    void deleteByRoleId(long id);

    Role findById(long roleId);

    Role findByRole(String role);
}
