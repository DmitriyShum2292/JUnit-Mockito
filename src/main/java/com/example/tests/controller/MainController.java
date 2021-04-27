package com.example.tests.controller;

import com.example.tests.model.NewUser;
import com.example.tests.service.NewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("/")
public class MainController {
    @Autowired
    private NewUserService newUserService;

    @GetMapping
    public String mainMethod(Model model){
        model.addAttribute("All",newUserService.findAll());
        return "main";
    }
    @PostMapping("/new")
    public String create(@RequestParam String username,String password) throws Exception {
        newUserService.create(new NewUser(username,password));
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        newUserService.delete(id);
        return "redirect:/";
    }
}
