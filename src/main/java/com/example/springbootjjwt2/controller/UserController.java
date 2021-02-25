package com.example.springbootjjwt2.controller;

import com.example.springbootjjwt2.repositories.UserRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
