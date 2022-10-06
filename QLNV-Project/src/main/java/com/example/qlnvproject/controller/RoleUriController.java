package com.example.qlnvproject.controller;

import com.example.qlnvproject.dto.RoleUriDto;
import com.example.qlnvproject.model.RoleUri;
import com.example.qlnvproject.service.RoleService;
import com.example.qlnvproject.service.RoleUriService;
import com.example.qlnvproject.service.UriService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roleUri")
public class RoleUriController {
    @Autowired
    UriService uriService;
    @Autowired
    RoleUriService roleUriService;

    @Autowired
    RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RoleUriDto roleUriDto, RoleUri roleUri){

         if (roleUriService.save(roleUriDto, roleUri) != null){
             roleUriService.save(roleUriDto, roleUri);
             BeanUtils.copyProperties(roleUriDto, roleUri);
             return ResponseEntity.ok().body(roleUri);
         }else {
             return ResponseEntity.badRequest().build();
         }

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

    @GetMapping("/findByRoleAndUriId")
    public RoleUri findByRole(@RequestParam("roleId") long roleId, @RequestParam("uriId") long uriId){
        RoleUri roleUri = roleUriService.findByRoleIdAndUriId(roleService.findById(roleId)
                , uriService.findById(uriId));
        return roleUri;
    }
}
