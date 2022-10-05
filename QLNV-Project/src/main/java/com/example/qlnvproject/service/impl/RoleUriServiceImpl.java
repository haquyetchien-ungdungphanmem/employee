package com.example.qlnvproject.service.impl;

import com.example.qlnvproject.dto.RoleUriDto;
import com.example.qlnvproject.model.Role;
import com.example.qlnvproject.model.RoleUri;
import com.example.qlnvproject.model.Uri;
import com.example.qlnvproject.repository.RoleRepository;
import com.example.qlnvproject.repository.RoleUriReponsitory;
import com.example.qlnvproject.repository.UriReponsitory;
import com.example.qlnvproject.service.RoleUriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleUriServiceImpl implements RoleUriService {
    @Autowired
    RoleUriReponsitory roleUriReponsitory;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UriReponsitory uriReponsitory;
    @Override
    public RoleUri save(RoleUriDto roleUriDto, RoleUri roleUri ) {
        Role role = roleRepository.findById(roleUriDto.getRoleId()).get();
        Uri uri = uriReponsitory.findById(roleUriDto.getUriId()).get();
        roleUri.setRole(role);
        roleUri.setUri(uri);
        return roleUriReponsitory.save(roleUri);
    }

    @Override
    public RoleUri findById(long id) {
        return roleUriReponsitory.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        roleUriReponsitory.deleteById(id);
    }

    @Override
    public List<RoleUri> getAll() {
        return roleUriReponsitory.findAll();
    }
}
