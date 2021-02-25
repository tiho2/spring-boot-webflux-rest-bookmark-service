package com.example.bookmarkapp.service;

import com.example.bookmarkapp.model.User;
import com.example.bookmarkapp.security.PBKDF2Encoder;
import com.example.bookmarkapp.security.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    // consider is just an example, you can load the user from the database from the repository

    private Map<String, User> data;
    @Autowired
    private PBKDF2Encoder passwordEncoder;

    //@PostConstruct
    public void init() {
        data = new HashMap<>();

        //username:passwowrd -> user:user
        data.put("user", new User("user", passwordEncoder.encode("user"), true, Arrays.asList(Role.ROLE_USER)));

        //username:passwowrd -> admin:admin
        data.put("admin", new User("admin", passwordEncoder.encode("admin"), true, Arrays.asList(Role.ROLE_ADMIN)));
    }

    public Mono<User> findByUsername(String username) {
        if (data.containsKey(username)) {
            return Mono.just(data.get(username));
        } else {
            return Mono.empty();
        }
    }

    public Mono<User> addUserAccount(String username, String password) {
        if (data.containsKey(username)) {
            return Mono.empty();
        } else {
            final User user = new User(username, passwordEncoder.encode(password), true, Arrays.asList(Role.ROLE_USER));
            data.put("user", user);
            return Mono.just(data.get(username));
        }
    }
}
