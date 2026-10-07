package com.user.service.UserService.services;

import com.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {

    //create
    User saveUser(User user);

    //getAll
    List<User> getAllUser();


    //getSingleUser
    User getUser(String userId);

    User deleteUser(String userId);

    User updateUser(String userId);
}
