package com.guru.info.repository;

import com.guru.info.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles,Integer> {

    @Query(value = "select * from roles",nativeQuery = true)
    List<Roles> findAllRoles();

    @Query(value = "select * from roles where role_type = :roleType",nativeQuery = true)
    Roles findAllByRoleType(@Param("roleType") String roleType);

    @Query(value = "select * from roles where id = :id",nativeQuery = true)
    Roles findAllById(@Param("id") int id);
}
