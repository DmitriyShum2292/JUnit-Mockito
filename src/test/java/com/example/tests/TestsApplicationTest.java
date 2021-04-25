package com.example.tests;

import com.example.tests.model.NewUser;
import com.example.tests.repo.NewUserRepository;
import com.example.tests.service.NewUserService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsApplicationTest extends TestCase {

    @Autowired
    private NewUserService service;

    @MockBean
    private NewUserRepository repository;

    @Test
    public void readTest() throws Exception {
        NewUser user = new NewUser("peter","password");
        user.setId(1);
        when(repository.findById(1)).thenReturn(user);
        assertEquals(user.getUsername(),"peter");
    }
    @Test
    public void readByUsername(){
        NewUser user = new NewUser("peter","password");
        when(repository.findByUsername(user.getUsername()))
                .thenReturn(user);
        assertEquals(user,service.readByUsername(user.getUsername()));
    }
    @Test
    public void findAll(){
        when(repository.findAll())
                .thenReturn((ArrayList<NewUser>) Stream.of(new NewUser("peter","password"))
                        .collect(Collectors.toList()));
        assertEquals(1,service.findAll().size());
    }
    @Test
    public void saveTest(){
        NewUser user = new NewUser("peter","password");
        when(repository.save(user)).thenReturn(user);
        assertEquals(user,service.create(user));
    }
    @Test
    public void deleteTeset(){
        NewUser user = new NewUser("peter","password");
        user.setId(1);
        service.delete(user.getId());
        verify(repository,times(1)).deleteById(user.getId());

    }
}