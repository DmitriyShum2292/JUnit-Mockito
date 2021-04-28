package com.example.tests.controller;

import com.example.tests.model.NewUser;
import com.example.tests.service.NewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class SecondController {

    @Autowired
    private NewUserService service;

    @GetMapping
    public ArrayList<NewUser> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable long id) throws Exception {
        NewUser user = service.read(id);
        if (user==null){
            return new ResponseEntity("Not found!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user,HttpStatus.OK);
    }
    @PostMapping("/new")
    public ResponseEntity create(@RequestBody NewUser user) throws Exception {
        //NewUser user = new NewUser(username,password);
        if(!service.create(user)){
            return new ResponseEntity("User exists!",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Ok!",HttpStatus.OK);
    }
}
