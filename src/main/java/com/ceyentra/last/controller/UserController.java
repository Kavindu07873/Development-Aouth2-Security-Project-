package com.ceyentra.last.controller;

import com.ceyentra.last.entity.User;
import com.ceyentra.last.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/user")
    public List<User> listUser(){
        return userService.findAllUsers();
    }

    @PostMapping(value = "/user")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping(value = "/user/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        userService.deleteUser(id);
        return "User Deleted Successfully!";
    }

}
