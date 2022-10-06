package com.example.qlnvproject.repository;

import com.example.qlnvproject.model.Role;
import com.example.qlnvproject.model.RoleUri;
import com.example.qlnvproject.model.Uri;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleUriReponsitory extends JpaRepository<RoleUri, Long> {
    List<RoleUri> findByRole(Role role);

    RoleUri getRoleUriByRoleAndUri(Role role, Uri uri);
}
