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

    public NewUser create(NewUser newUser){
        return repository.save(newUser);
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
