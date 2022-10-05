package com.example.qlnvproject.controller;

import com.example.qlnvproject.dto.RoleUriDto;
import com.example.qlnvproject.model.RoleUri;
import com.example.qlnvproject.service.RoleUriService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roleUri")
public class RoleUriController {

    @Autowired
    RoleUriService roleUriService;

    @PostMapping("/create")
    public RoleUri create(@RequestBody RoleUriDto roleUriDto){
        RoleUri roleUri = new RoleUri();
        return roleUriService.save(roleUriDto, roleUri);
    }

    @PostMapping("/update")
    public RoleUri update(@RequestParam("id") long id, @RequestBody RoleUriDto roleUriDto){
        RoleUri roleUri = roleUriService.findById(id);
        return roleUriService.save(roleUriDto, roleUri);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") long id){
        roleUriService.deleteById(id);
        return ResponseEntity.ok().body("Delete Success!");
    }

    @GetMapping("/getAll")
    public List<RoleUri> getAll(){
        return roleUriService.getAll();
    }
}
