package com.example.complaint_reporting_backend.dao;

import com.example.complaint_reporting_backend.model.Complaint;
import com.example.complaint_reporting_backend.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComplaintDao extends CrudRepository<Complaint,Integer> {

    @Query(value ="SELECT `id`, `complaint`,`user_id` FROM `complaint` WHERE `user_id`=:userId",nativeQuery = true)
    List<Complaint> viewComplaint(@Param("userId") Integer userId);

}
