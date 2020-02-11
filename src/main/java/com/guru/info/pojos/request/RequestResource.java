package com.guru.info.pojos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestResource {
    private int linkId;
    private String roleType;
    private String course;
    private String link;
}
