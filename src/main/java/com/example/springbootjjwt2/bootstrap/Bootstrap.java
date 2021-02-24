package com.example.springbootjjwt2.bootstrap;

import com.example.springbootjjwt2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    private final UserService userService;

    public Bootstrap(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("#################################### Bootstrap");
        userService.init();
    }
}
