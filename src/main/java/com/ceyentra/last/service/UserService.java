package com.ceyentra.last.service;

//import com.ceyentra.oauth.demo.entity.User;

import com.ceyentra.last.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> findAll();

//    void delete(int id);
}
