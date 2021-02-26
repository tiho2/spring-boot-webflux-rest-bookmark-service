package com.example.bookmarkapp.controller;


import com.example.bookmarkapp.model.User;
import com.example.bookmarkapp.repositories.UserRepository;
import com.example.bookmarkapp.security.JwtUtil;
import com.example.bookmarkapp.security.PBKDF2Encoder;
import com.example.bookmarkapp.security.model.AuthRequest;
import com.example.bookmarkapp.security.model.AuthResponse;
import com.example.bookmarkapp.security.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class AuthenticationController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PBKDF2Encoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/api/v1/login")
    public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
        return userRepository.findById(ar.getUsername()).map((userDetails) -> {
            if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
                return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/api/v1/signin")
    public Mono<User> create(@RequestBody AuthRequest ar) {
        return userRepository.save(User.builder().username(ar.getUsername()).password(passwordEncoder.encode(ar.getPassword())).roles(Arrays.asList(Role.ROLE_USER)).enabled(Boolean.TRUE).build());
    }

}