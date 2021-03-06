package com.example.bookmarkapp.bootstrap;

import com.example.bookmarkapp.model.Bookmark;
import com.example.bookmarkapp.model.User;
import com.example.bookmarkapp.repositories.BookmarkRepository;
import com.example.bookmarkapp.repositories.UserRepository;
import com.example.bookmarkapp.security.PBKDF2Encoder;
import com.example.bookmarkapp.security.model.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

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
    public void run(String... args) {
        if (Objects.equals(this.userRepository.count().block(), 0L)) {
            System.out.println("####### Bootstrap data ######");
            User user = User.builder().username("user").password(passwordEncoder.encode("user")).enabled(Boolean.TRUE).roles(Arrays.asList(Role.ROLE_USER)).build();
            User admin = User.builder().username("admin").password(passwordEncoder.encode("admin")).enabled(Boolean.TRUE).roles(Arrays.asList(Role.ROLE_ADMIN, Role.ROLE_USER)).build();

            userRepository.save(user).block();
            userRepository.save(admin).block();
            userRepository.save(admin).block();

            bookmarkRepository.save(Bookmark.builder().url("https://spring.io").owner(user.getUsername()).shared(Boolean.TRUE).build()).block();
            bookmarkRepository.save(Bookmark.builder().url("https://angular.io").owner(user.getUsername()).shared(Boolean.FALSE).build()).block();
            bookmarkRepository.save(Bookmark.builder().url("https://www.redhat.com").owner(admin.getUsername()).shared(Boolean.FALSE).build()).block();
            bookmarkRepository.save(Bookmark.builder().url("https://aws.amazon.com/?nc2=h_lg").owner(admin.getUsername()).shared(Boolean.TRUE).build()).block();
            bookmarkRepository.save(Bookmark.builder().url("https://aws.amazon.com/telecom/?hp=tile&tile=industries").owner(admin.getUsername()).shared(Boolean.TRUE).build()).block();

        }

        System.out.println("users count:" + this.userRepository.count().block());
        System.out.println("bookmarks count: " + this.bookmarkRepository.count().block());

    }
}
