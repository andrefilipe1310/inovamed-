package com.inovamed.clinical_study_system.controller;

import com.inovamed.clinical_study_system.model.user.User;
import com.inovamed.clinical_study_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User create(@RequestBody User user){
        return userService.create(user);
    }
    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id){
        return userService.findById(id);
    }


}
