package com.example.complaint_reporting_backend.dao;

import com.example.complaint_reporting_backend.model.Complaint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ComplaintDao extends CrudRepository<Complaint,Integer> {

}
