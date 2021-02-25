package com.example.springbootjjwt2.controller;

import com.example.springbootjjwt2.repositories.BookmarkRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookmarkController {

    private BookmarkRepository bookmarkRepository;

    public BookmarkController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }
}
