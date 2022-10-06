package com.example.qlnvproject.controller;

import com.example.qlnvproject.dto.LogWithUriDto;
import com.example.qlnvproject.model.LogWithUri;
import com.example.qlnvproject.service.LogWithUriService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logWithUri")
public class LogWithUriController {

    @Autowired
    LogWithUriService logWithUriService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody LogWithUriDto logWithUriDto, LogWithUri logWithUri){

        if (logWithUriService.save(logWithUriDto, logWithUri) !=null){
             logWithUriService.save(logWithUriDto, logWithUri);
             return ResponseEntity.ok().body(logWithUri);
        }else {
            return ResponseEntity.badRequest().build();
        }


    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") long id){
        logWithUriService.delete(id);
        return ResponseEntity.ok().body("Delete Success!");
    }
}
