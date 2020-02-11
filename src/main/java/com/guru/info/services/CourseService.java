package com.guru.info.services;

import com.guru.info.constants.exceptions.CoursesExceptions;
import com.guru.info.constants.exceptions.RolesExceptions;
import com.guru.info.exceptions.BadRequestException;
import com.guru.info.model.Courses;
import com.guru.info.pojos.response.CoursesListResponse;
import com.guru.info.repository.CoursesRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CoursesRepository coursesRepository;

    public CourseService(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    public CoursesListResponse getCourses(){
        return new CoursesListResponse(coursesRepository.findAllCourses());
    }

    public String addCourse(String courseType){

        Courses courses = coursesRepository.findAllByCourseType(courseType);
        if(courses!=null){
            throw new BadRequestException(CoursesExceptions.ALREADY_EXISTS);
        }
        Courses course = new Courses();
        course.setCourseType(courseType);
        coursesRepository.save(course);
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
