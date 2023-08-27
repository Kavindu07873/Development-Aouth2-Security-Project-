package com.ceyentra.last.service;

//import com.ceyentra.oauth.demo.entity.User;

import com.ceyentra.last.entity.User;

import java.util.List;

public interface UserService {

    public  User  saveUser(User user);
    List<User> findAllUsers();
    public User updateUser(User user) ;
    void deleteUser(Long id);
}
