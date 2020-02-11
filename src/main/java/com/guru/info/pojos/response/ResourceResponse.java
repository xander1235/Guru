package com.guru.info.pojos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceResponse {
    private int linkId;
    private String course;
    private String link;
    private float averageRating;
    private String addedBy;
}
