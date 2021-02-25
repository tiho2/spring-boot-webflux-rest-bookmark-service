package com.example.springbootjjwt2.bootstrap;

import com.example.springbootjjwt2.model.Bookmark;
import com.example.springbootjjwt2.model.User;
import com.example.springbootjjwt2.repositories.BookmarkRepository;
import com.example.springbootjjwt2.repositories.UserRepository;
import com.example.springbootjjwt2.security.PBKDF2Encoder;
import com.example.springbootjjwt2.security.model.Role;
import com.example.springbootjjwt2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Bootstrap implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;
    private PBKDF2Encoder passwordEncoder;

    public Bootstrap(UserRepository userRepository,
                     BookmarkRepository bookmarkRepository,
                     PBKDF2Encoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.userRepository.count().block().equals(0L)) {

            System.out.println("####### Bootstrap data ######");
            User user = User.builder().username("user").password(passwordEncoder.encode("user")).enabled(Boolean.TRUE).roles(Arrays.asList(Role.ROLE_USER)).build();
            User admin = User.builder().username("admin").password(passwordEncoder.encode("admin")).enabled(Boolean.TRUE).roles(Arrays.asList(Role.ROLE_ADMIN)).build();

            userRepository.save(user).block();
            userRepository.save(admin).block();

            bookmarkRepository.save(Bookmark.builder().url("https://spring.io/").owner(user.getUsername()).shared(Boolean.TRUE).build()).block();
            bookmarkRepository.save(Bookmark.builder().url("https://angular.io/").owner(user.getUsername()).shared(Boolean.FALSE).build()).block();
            bookmarkRepository.save(Bookmark.builder().url("https://www.redhat.com/").owner(admin.getUsername()).shared(Boolean.FALSE).build()).block();
            System.err.println(this.userRepository.count().block());
            System.err.println(this.bookmarkRepository.count().block());




        }

    }
}
