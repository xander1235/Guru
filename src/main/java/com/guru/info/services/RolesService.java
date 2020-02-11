package com.guru.info.services;

import com.guru.info.constants.exceptions.RolesExceptions;
import com.guru.info.exceptions.BadRequestException;
import com.guru.info.model.Roles;
import com.guru.info.pojos.response.RolesListResponse;
import com.guru.info.repository.RolesRepository;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public RolesListResponse getRoles(){
        return new RolesListResponse(rolesRepository.findAllRoles());
    }

    public String addRole(String roleType){

        Roles roles = rolesRepository.findAllByRoleType(roleType);
        if(roles!=null){
            throw new BadRequestException(RolesExceptions.ALREADY_EXISTS);
        }
        Roles role = new Roles();
        role.setRoleType(roleType);
        rolesRepository.save(role);
        return "Success";
    }

    public String updateRole(int id,String roleType){
        Roles roles = rolesRepository.findAllByRoleType(roleType);
        if(roles!=null){
            throw new BadRequestException(RolesExceptions.ALREADY_EXISTS);
        }
        Roles roles1 = rolesRepository.findAllById(id);
        if(roles1==null){
            throw new BadRequestException(RolesExceptions.INVALID_ID);
        }
        roles1.setRoleType(roleType);
        rolesRepository.save(roles1);

        return "Updated";
    }
}
