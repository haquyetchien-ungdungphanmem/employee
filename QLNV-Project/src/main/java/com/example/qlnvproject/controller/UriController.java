package com.example.qlnvproject.controller;

import com.example.qlnvproject.dto.UriDto;
import com.example.qlnvproject.model.Uri;
import com.example.qlnvproject.service.UriService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uri")
public class UriController {
    @Autowired
    UriService uriService;

    @PostMapping("/create")
    public Uri create(@RequestBody UriDto uriDto){
        Uri uri = new Uri();
        BeanUtils.copyProperties(uriDto, uri);
        return uriService.save(uri);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") long id){
        uriService.deleteById(id);
        return ResponseEntity.ok().body("Delete success!");
    }

    @PostMapping("/update")
    public Uri update(@RequestParam("id") long id, @RequestBody UriDto uriDto){
        Uri uri = uriService.findById(id);
        BeanUtils.copyProperties(uriDto, uri);
        return uriService.save(uri);
    }

    @GetMapping("/getAll")
    public List<Uri> getAll(){
        return uriService.getAll();
    }
}
