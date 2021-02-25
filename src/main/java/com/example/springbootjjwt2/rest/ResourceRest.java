package com.example.springbootjjwt2.rest;


import com.example.springbootjjwt2.model.Bookmark;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ResourceRest {

    @RequestMapping(value = "/api/v1/resource/user", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public Mono<ResponseEntity<?>> user() {
        return Mono.just(ResponseEntity.ok("Content for user"));
    }

    @RequestMapping(value = "/api/v1/resource/admin", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<?>> admin() {
        return Mono.just(ResponseEntity.ok(""));
    }

    @RequestMapping(value = "/api/v1/resource/user-or-admin", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Mono<ResponseEntity<?>> userOrAdmin() {
        return Mono.just(ResponseEntity.ok(""));
    }
}