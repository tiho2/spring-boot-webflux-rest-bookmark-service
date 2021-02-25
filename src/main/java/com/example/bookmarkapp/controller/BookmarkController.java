package com.example.bookmarkapp.controller;

import com.example.bookmarkapp.repositories.BookmarkRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookmarkController {

    private BookmarkRepository bookmarkRepository;

    public BookmarkController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }
}
