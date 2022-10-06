package com.example.qlnvproject.repository;

import com.example.qlnvproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);

    Role findByRoleId(long roleId);
}
