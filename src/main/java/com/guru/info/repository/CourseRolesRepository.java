package com.guru.info.repository;

import com.guru.info.model.CoursesRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRolesRepository extends JpaRepository<CoursesRoles,Integer> {

    @Query(value = "select course_id from courses_roles where role_id = :roleId",nativeQuery = true)
    List<Integer> findCourseIdsByRoleId(@Param("roleId") int roleId);

    @Query(value = "select * from courses_roles where course_id = :courseId", nativeQuery = true)
    CoursesRoles findAllByCourseId(@Param("courseId") int courseId);
}
