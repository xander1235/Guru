package com.guru.info.controllers;

import com.guru.info.pojos.response.CoursesListResponse;
import com.guru.info.pojos.response.RolesListResponse;
import com.guru.info.services.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "courses/")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "get/courses")
    public CoursesListResponse getCourses(){
        return courseService.getCourses();
    }

    @PostMapping(value = "add/course/{roleType}/{courseType}")
    public String addCourse(@PathVariable String roleType, @PathVariable String courseType){
        return courseService.addCourse(roleType,courseType);
    }

    @PostMapping(value = "update/course/{id}/{courseType}")
    public String updateCourse(@PathVariable int id, @PathVariable String courseType){
        return courseService.updateCourse(id,courseType);
    }
}
