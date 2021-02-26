package com.example.bookmarkapp.controller;


import com.example.bookmarkapp.repositories.UserRepository;
import com.example.bookmarkapp.security.JwtUtil;
import com.example.bookmarkapp.security.PBKDF2Encoder;
import com.example.bookmarkapp.security.model.AuthRequest;
import com.example.bookmarkapp.security.model.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthenticationRest {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PBKDF2Encoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/api/v1/login", method = RequestMethod.POST)
    public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
        return userRepository.findById(ar.getUsername()).map((userDetails) -> {
            if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
                return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}