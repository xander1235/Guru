package com.guru.info.repository;

import com.guru.info.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Courses,Integer> {

    @Query(value = "select course_type from courses where id = :courseId",nativeQuery = true)
    String findCourseById(@Param("courseId") int courseId);

    @Query(value = "select * from courses where course_type = :courseType",nativeQuery = true)
    Courses findAllByCourseType(@Param("courseType") String courseType);

    @Query(value = "select * from courses",nativeQuery = true)
    List<Courses> findAllCourses();

    @Query(value = "select * from courses where id= :id",nativeQuery = true)
    Courses findAllById(@Param("id") int id);

}
