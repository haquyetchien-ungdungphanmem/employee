package com.example.qlnvproject.service.impl;

import com.example.qlnvproject.model.History;
import com.example.qlnvproject.repository.HistoryRepository;
import com.example.qlnvproject.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryRepository historyRepository;

    @Override
    public void save(History history) {
        historyRepository.save(history);
    }

    @Override
    public List<History> findByStatus(int status) {
        return historyRepository.findByStatuss(status);
    }
}
