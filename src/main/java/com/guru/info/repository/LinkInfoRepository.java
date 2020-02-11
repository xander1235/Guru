package com.guru.info.repository;

import com.guru.info.model.LinkInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LinkInfoRepository extends JpaRepository<LinkInfo,Integer> {

    @Query(value = "select * from link_info where course_id in :courseIds",nativeQuery = true)
    List<LinkInfo> findAllByCourseIds(@Param("courseIds") List<Integer> courseIds);

    @Query(value = "select * from link_info where link = :link",nativeQuery = true)
    LinkInfo checkLinkIfAlreadyExist(@Param("link") String link);

    @Query(value = "select * from link_info where id = :id",nativeQuery = true)
    LinkInfo findAllByLinkId(@Param("id") int id);

}
