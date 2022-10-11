package com.example.qlnvproject.service;

import com.example.qlnvproject.model.History;

import java.util.List;

public interface HistoryService {
    void save(History history);

    List<History> findByStatus(int status);
}
