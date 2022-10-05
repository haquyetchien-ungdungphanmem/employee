package com.example.qlnvproject.service;

import com.example.qlnvproject.dto.RoleUriDto;
import com.example.qlnvproject.model.RoleUri;

import java.util.List;

public interface RoleUriService {
    RoleUri save(RoleUriDto roleUriDto, RoleUri roleUri);

    RoleUri findById(long id);

    void deleteById(long id);

    List<RoleUri> getAll();
}
