package com.lone.UserService.controller;

import com.lone.UserService.entity.*;
import com.lone.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User newUser = userService.saveUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());

    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {

        User user = userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.FOUND);

    }

    @GetMapping("/hotel/{userId}")
    public ResponseEntity<UserDto> getHotelUserById(@PathVariable Integer userId) {

        UserDto user = userService.getHotelUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.FOUND);

    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        return ResponseEntity.accepted().body(userService.updateUser(user));

    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {

        return ResponseEntity.ok(userService.deleteUser(userId));

    }

}
