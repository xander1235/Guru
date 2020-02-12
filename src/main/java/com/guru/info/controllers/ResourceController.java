package com.guru.info.controllers;

import com.guru.info.exceptions.ForbiddenException;
import com.guru.info.pojos.request.RequestResource;
import com.guru.info.pojos.response.ResourcesResponse;
import com.guru.info.services.ResourcesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("resources/")
public class ResourceController {

    private final ResourcesService resourcesService;

    Logger log = LoggerFactory.getLogger(ResourceController.class);

    @Value("${self.apiToken}")
    private String apiToken;

    public ResourceController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @GetMapping(value = "get/resources/{role}")
    public ResourcesResponse getResources(@PathVariable String role,@RequestHeader String token){
        checkApiToken(token);
        return resourcesService.getResources(role);
    }

    @PostMapping(value = "add/resource")
    public String addCourse(HttpServletRequest request, @RequestBody RequestResource requestResource){
        return resourcesService.addResource(request,requestResource);
    }

    @PostMapping(value = "update/resource")
    public String updateCourse(HttpServletRequest request, @RequestBody RequestResource requestResource){
        return resourcesService.updateResource(request,requestResource);
    }

    public void checkApiToken(String token){
        if(!token.equals(apiToken)){
            throw new ForbiddenException("Access denied. Please check your api token.");
        }
    }
}
