package com.example.complaint_reporting_backend.dao;

import com.example.complaint_reporting_backend.model.Complaint;
import com.example.complaint_reporting_backend.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ComplaintDao extends CrudRepository<Complaint,Integer>{

    @Query(value ="SELECT `id`, `complaint`,`user_id` FROM `complaint` WHERE `user_id`=:userId",nativeQuery = true)
    List<Complaint> viewComplaint(@Param("userId") Integer userId);

    @Query(value ="SELECT u.`email`, u.`name`, u.`password`, u.`phone_no`,c.`complaint`,c.user_id,c.id FROM `users` u JOIN complaint c ON c.user_id=u.id",nativeQuery = true)
    List<Map<String,String>> viewAllComplaints();

}
