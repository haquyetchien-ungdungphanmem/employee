package com.example.qlnvproject.service;

import com.example.qlnvproject.dto.LogWithUriDto;
import com.example.qlnvproject.model.LogWithUri;

public interface LogWithUriService {
    LogWithUri save(LogWithUriDto logWithUriDto, LogWithUri logWithUri);

    LogWithUri findByUri(String uri);

    void delete(long id);
}
