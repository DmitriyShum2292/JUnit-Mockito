package com.example.tests.controller;

import com.example.tests.model.NewUser;
import com.example.tests.service.NewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class SecondController {

    @Autowired
    private NewUserService service;

    private String message = "Not found!";

    @GetMapping
    public ArrayList<NewUser> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable long id) throws Exception {
        NewUser user = service.read(id);
        if (user==null){
            return new ResponseEntity(message,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user,HttpStatus.OK);
    }
}
