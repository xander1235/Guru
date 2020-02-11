package com.guru.info.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"courses","modifiedAt","createdAt"})
@Entity
@Table(name = "link_info")
public class LinkInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "link")
    private String link;

    @Column(name = "added_by")
    private String addedBy;

    @Column(name = "average_rating",columnDefinition = "float default 5.0")
    private float averageRating;


    @Column(name = "rated_count", columnDefinition = "int default 1")
    private int ratedCount;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Date modifiedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "course_id" , referencedColumnName = "id")
    private Courses courses;

}
