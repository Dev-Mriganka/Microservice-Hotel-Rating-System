package com.lone.UserService.service;

import com.lone.UserService.entity.*;
import com.lone.UserService.exceptions.UserException;

import java.util.List;

public interface UserService {

    //create
    User saveUser(User user);

    //get all users
    List<User> getAllUsers();

    //get single user from id
    User getUserById(Integer userId) throws UserException;


    UserDto getHotelUserById(Integer userId) throws UserException;

    //delete user
    String deleteUser(Integer userId) throws UserException;

    //update user
    User updateUser(User user) throws UserException;



}
