package com.guru.info.pojos.response;

import com.guru.info.model.Courses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoursesListResponse {
    List<Courses> courses;
}
