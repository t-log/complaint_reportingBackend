package com.example.complaint_reporting_backend.controller;

import com.example.complaint_reporting_backend.dao.ComplaintDao;
import com.example.complaint_reporting_backend.dao.UserDao;
import com.example.complaint_reporting_backend.model.Complaint;
import com.example.complaint_reporting_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ComplaintController {
    @Autowired
    private UserDao udao;
    @Autowired
    private ComplaintDao cdao;
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userregister",consumes = "application/json",produces = "application/json")
    public String userRegister(@RequestBody User u)
    {
        udao.save(u);
        return "{\"status\":\"success\"}";
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/usersearch",consumes = "application/json",produces = "application/json")
    public HashMap<String, String> userSearch(@RequestBody User u){
        System.out.println(u.getEmail());
        System.out.println(u.getPassword());
        List<User> checkData = (List<User>) udao.searchUser(u.getEmail(),u.getPassword());
        System.out.println(checkData);
        HashMap<String,String> hashmap= new HashMap<>();
        if(checkData.size()==0){
            hashmap.put("status","failed");
            hashmap.put("message","user does not exist");
        }
        else{
            int id=checkData.get(0).getId();
            hashmap.put("userid",String.valueOf(id));
            hashmap.put("status","success");
            hashmap.put("message","user exists");
        }
        return hashmap;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userbyid",consumes = "application/json",produces = "application/json")
    public List<User> userGetById(@RequestBody User u){
        return (List<User>) udao.getUser(u.getId());
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addUserComplaint",consumes = "application/json",produces = "application/json")
    public String addComplaint(@RequestBody Complaint c){
        cdao.save(c);
        return "{\"status\":\"success\"}";
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/getUserComplaint",consumes = "application/json",produces = "application/json")
    public List<Complaint> getComplaint(@RequestBody Complaint c)
    {
        System.out.println(c.getUserId());
        return (List<Complaint>) cdao.viewComplaint(c.getUserId());
    }
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminviewallcomplaints")
    public List<Map<String,String>> viewProduct(){
        return (List<Map<String,String>>) cdao.viewAllComplaints();}
}
