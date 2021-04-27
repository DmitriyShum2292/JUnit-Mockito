package com.example.tests.service;

import com.example.tests.model.NewUser;
import com.example.tests.repo.NewUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewUserService {
    @Autowired
    private NewUserRepository repository;

    public boolean create(NewUser newUser) throws Exception {
        NewUser user = readByUsername(newUser.getUsername());
        try {
            user.getUsername();
        }
        catch (NullPointerException e){
            repository.save(newUser);
            return true;
        }
        return false;
    }

    public NewUser read(long id) throws Exception{
        return repository.findById(id);
    }

    public NewUser readByUsername(String username){
        return repository.findByUsername(username);
    }

    public boolean update(long id){
        try {
            repository.removeById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
    public void delete(long id){
        repository.deleteById(id);
    }
    public ArrayList<NewUser>findAll(){
        return repository.findAll();
    }
}
