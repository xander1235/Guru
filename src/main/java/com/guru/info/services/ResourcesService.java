package com.guru.info.services;

import com.guru.info.constants.exceptions.CoursesExceptions;
import com.guru.info.constants.exceptions.CoursesRolesExceptions;
import com.guru.info.constants.exceptions.LinkInfoExceptions;
import com.guru.info.constants.exceptions.RolesExceptions;
import com.guru.info.exceptions.BadRequestException;
import com.guru.info.model.Courses;
import com.guru.info.model.CoursesRoles;
import com.guru.info.model.LinkInfo;
import com.guru.info.model.Roles;
import com.guru.info.pojos.request.RequestResource;
import com.guru.info.pojos.response.ResourceResponse;
import com.guru.info.pojos.response.ResourcesResponse;
import com.guru.info.repository.CourseRolesRepository;
import com.guru.info.repository.CoursesRepository;
import com.guru.info.repository.LinkInfoRepository;
import com.guru.info.repository.RolesRepository;
import com.guru.info.utils.GuruUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourcesService {

    private final RolesRepository rolesRepository;

    private final CourseRolesRepository courseRolesRepository;

    private final LinkInfoRepository linkInfoRepository;

    private final CoursesRepository coursesRepository;

    Logger logger = LoggerFactory.getLogger(ResourcesService.class);

    public ResourcesService(RolesRepository rolesRepository, CourseRolesRepository courseRolesRepository, LinkInfoRepository linkInfoRepository, CoursesRepository coursesRepository) {

        this.rolesRepository = rolesRepository;
        this.courseRolesRepository = courseRolesRepository;
        this.linkInfoRepository = linkInfoRepository;
        this.coursesRepository = coursesRepository;

    }


    public ResourcesResponse getResources(String roleType){

        Roles role = rolesRepository.findAllByRoleType(roleType);
        if(role==null){
            throw new BadRequestException(RolesExceptions.INVALID_ROLE_TYPE);
        }
        List<Integer> courseIds = role.getCoursesRoles().stream().map(coursesRoles -> {return coursesRoles.getCourseId();}).collect(Collectors.toList());
        List<LinkInfo> linkInfos = linkInfoRepository.findAllByCourseIds(courseIds);
        List<ResourceResponse> resourceResponses = linkInfos.stream().map(linkInfo -> {

            return new ResourceResponse(linkInfo.getId(),linkInfo.getCourses().getCourseType(),linkInfo.getLink(),linkInfo.getAddedBy());

        }).collect(Collectors.toList());

        return new ResourcesResponse(resourceResponses);

    }


    public String addResource(HttpServletRequest request, RequestResource requestResource) {

        String email = GuruUtils.validateEmailInHeader(request);

        Roles roles = isValidRoleType(requestResource.getRoleType());

        Courses courses = isValidCourse(requestResource.getCourse());

        CoursesRoles coursesRoles = courseRolesRepository.findAllByCourseId(courses.getId());
        if(coursesRoles ==null){
            throw new BadRequestException(CoursesRolesExceptions.MAP_IN_COURSES_ROLES);
        }

        if(roles.getId() != coursesRoles.getRoleId()){
            throw new BadRequestException(LinkInfoExceptions.DIFFERENT_ROLE);
        }

        LinkInfo linkInfo = linkInfoRepository.checkLinkIfAlreadyExist(requestResource.getLink());
        if(linkInfo != null){
            throw new BadRequestException(LinkInfoExceptions.LINK_ALREADY_EXISTS);
        }

        LinkInfo linkInfo1 = new LinkInfo();
        linkInfo1.setCourses(courses);
        linkInfo1.setAddedBy(email);
        linkInfo1.setLink(requestResource.getLink());
        linkInfoRepository.save(linkInfo1);

        return "Success ";

    }


    public String updateResource(HttpServletRequest request, RequestResource requestResource) {

        String email = GuruUtils.validateEmailInHeader(request);
        isValidRequestCourse(requestResource);

        LinkInfo linkInfo = linkInfoRepository.findAllByLinkId(requestResource.getLinkId());

        if(linkInfo == null){ throw new BadRequestException(LinkInfoExceptions.INVALID_LINK_ID); }

        if(!linkInfo.getAddedBy().equals(email)){ throw new BadRequestException(LinkInfoExceptions.NO_PERMISSION); }

        if(requestResource.getCourse()==null){
            requestResource.setCourse("");
        }
        else {

            Courses course = coursesRepository.findAllByCourseType(requestResource.getCourse());

            if (!requestResource.getCourse().isEmpty() && course == null) { throw new BadRequestException(CoursesExceptions.INVALID_COURSE); }

            if (course != null) {
                linkInfo.getCourses().setId(course.getId());
            }
        }

        Roles roles = rolesRepository.findAllByRoleType(requestResource.getRoleType());

        if(roles ==null){ throw new BadRequestException(RolesExceptions.INVALID_ROLE_TYPE); }

        CoursesRoles coursesRoles = courseRolesRepository.findAllByCourseId(linkInfo.getCourses().getId());

        if(coursesRoles.getRoleId() != roles.getId()){ throw new BadRequestException(LinkInfoExceptions.DIFFERENT_ROLE); }

        linkInfo.setLink(requestResource.getLink());
        linkInfoRepository.save(linkInfo);

        return "succesfully updated ";
    }


    public void isValidRequestCourse(RequestResource requestResource){
        if(requestResource.getRoleType()==null || requestResource.getLinkId() == 0 || (requestResource.getCourse()==null && requestResource.getLink()==null)){
            throw new BadRequestException("Attribute is empty");
        }
    }

    public Roles isValidRoleType(String roleType){

        Roles roles = rolesRepository.findAllByRoleType(roleType);
        if(roles==null){ throw new BadRequestException(RolesExceptions.INVALID_ROLE_TYPE); }
        return roles;
    }


    public Courses isValidCourse(String course){

        Courses courses = coursesRepository.findAllByCourseType(course);
        if(courses == null){ throw new BadRequestException(CoursesExceptions.INVALID_COURSE); }
        return courses;
    }

}
