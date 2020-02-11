package com.guru.info.controllers;

import com.guru.info.pojos.response.RolesListResponse;
import com.guru.info.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "roles/")
public class RolesController {

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping(value = "get/roles")
    public RolesListResponse getRoles(){
        return rolesService.getRoles();
    }

    @PostMapping(value = "add/role/{roleType}")
    public String addRole(@PathVariable String roleType){
        return rolesService.addRole(roleType);
    }

    @PostMapping(value = "update/role/{id}/{roleType}")
    public String updateRole(@PathVariable int id, @PathVariable String roleType){
        return rolesService.updateRole(id,roleType);
    }

}
