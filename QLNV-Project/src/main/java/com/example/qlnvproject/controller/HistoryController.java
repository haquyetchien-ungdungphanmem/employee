package com.example.qlnvproject.controller;

import com.example.qlnvproject.model.History;
import com.example.qlnvproject.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @GetMapping("/get")
    public List<History> get(){
        return historyService.findByStatus(200);
    }
}
