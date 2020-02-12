package com.guru.info.services;

import com.guru.info.constants.exceptions.CoursesExceptions;
import com.guru.info.constants.exceptions.RolesExceptions;
import com.guru.info.exceptions.BadRequestException;
import com.guru.info.model.Courses;
import com.guru.info.model.CoursesRoles;
import com.guru.info.model.Roles;
import com.guru.info.pojos.response.CoursesListResponse;
import com.guru.info.repository.CourseRolesRepository;
import com.guru.info.repository.CoursesRepository;
import com.guru.info.repository.RolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class CourseService {

    private final CoursesRepository coursesRepository;

    private final RolesRepository rolesRepository;

    private final CourseRolesRepository courseRolesRepository;

    Logger log = LoggerFactory.getLogger(CourseService.class);

    public CourseService(CoursesRepository coursesRepository,RolesRepository rolesRepository,CourseRolesRepository courseRolesRepository) {
        this.coursesRepository = coursesRepository;
        this.rolesRepository = rolesRepository;
        this.courseRolesRepository = courseRolesRepository;
    }

    public CoursesListResponse getCourses(){
        return new CoursesListResponse(coursesRepository.findAllCourses());
    }

    @Transactional
    public String addCourse(String roleType, String courseType){

        Roles roles = rolesRepository.findAllByRoleType(roleType);
        if(roles==null){
            throw new BadRequestException(RolesExceptions.INVALID_ROLE_TYPE);
        }

        Courses courses = coursesRepository.findAllByCourseType(courseType);
        if(courses!=null){
            throw new BadRequestException(CoursesExceptions.ALREADY_EXISTS);
        }
        Courses course = new Courses();
        course.setCourseType(courseType);
        coursesRepository.save(course);
        int courseId = coursesRepository.findAllByCourseType(courseType).getId();
        log.info("\n\n\n" + courseId + "\n\n\n" + roles.getId() + "\n\n\n");
        CoursesRoles coursesRoles = new CoursesRoles();
        coursesRoles.setCourseId(courseId);
        coursesRoles.setRoleId(roles.getId());
        courseRolesRepository.save(coursesRoles);

        return "Success";
    }

    public String updateCourse(int id,String courseType){
        Courses courses = coursesRepository.findAllByCourseType(courseType);
        if(courses!=null){
            throw new BadRequestException(RolesExceptions.ALREADY_EXISTS);
        }
        Courses courses1 = coursesRepository.findAllById(id);
        if(courses1==null){
            throw new BadRequestException(CoursesExceptions.INVALID_ID);
        }
        courses1.setCourseType(courseType);
        coursesRepository.save(courses1);

        return "Updated";
    }
}
