package com.guru.info.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Roles implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_type")
    private String roleType;

    @OneToMany(targetEntity = com.guru.info.model.CoursesRoles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private List<CoursesRoles> coursesRoles;

}
