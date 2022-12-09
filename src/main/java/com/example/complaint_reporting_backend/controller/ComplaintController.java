package com.example.complaint_reporting_backend.controller;

import com.example.complaint_reporting_backend.dao.UserDao;
import com.example.complaint_reporting_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ComplaintController {
    @Autowired
    private UserDao udao;
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
}
