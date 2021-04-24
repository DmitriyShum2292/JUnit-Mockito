package com.example.tests.repo;

import com.example.tests.model.NewUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NewUserRepository extends JpaRepository<NewUser,Long> {

    NewUser findById(long id);

    void removeById(long id);

    void deleteById(long id);

    ArrayList<NewUser> findAll();

    NewUser findByUsername(String username);
}
