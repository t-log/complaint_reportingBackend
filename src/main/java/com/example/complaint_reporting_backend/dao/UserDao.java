package com.example.complaint_reporting_backend.dao;

import com.example.complaint_reporting_backend.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserDao extends CrudRepository<User,Integer> {

    @Query(value ="SELECT `id`, `address`, `email`, `name`, `password`, `phone_no`, `username` FROM `users` WHERE `email`=:email AND `password`=:password",nativeQuery = true)
    List<User> searchUser(@Param("email") String email, @Param("password") String password);

    @Query(value ="SELECT * FROM `users` WHERE `id`=:id",nativeQuery = true)
    List<User> getUser(@Param("id") Integer id);
}
