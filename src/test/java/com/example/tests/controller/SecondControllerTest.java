package com.example.tests.controller;

import com.example.tests.model.NewUser;
import com.example.tests.service.NewUserService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(SecondController.class)
public class SecondControllerTest extends TestCase {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NewUserService service;

    @Test
    public void testFindAll() throws Exception {
        when(service.findAll()).thenReturn(new ArrayList<NewUser>(Arrays.asList(
                new NewUser("Peter","password")
                )));
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[*].username",containsInAnyOrder("Peter")));
    }

    @Test
    public void testFindOne() throws Exception{
        when(service.read(1)).thenReturn(new NewUser("Peter","password"));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username",equalTo("Peter")));
    }
}