package com.example.bookmarkapp.controller;

import com.example.bookmarkapp.repositories.BookmarkRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkControllerTest {

    WebTestClient webTestClient;
    BookmarkRepository bookmarkRepository;
    BookmarkController bookmarkController;
    @BeforeEach
    void setUp() {
        bookmarkRepository = Mockito.mock(BookmarkRepository.class);
        bookmarkController = new BookmarkController(bookmarkRepository);
        webTestClient = WebTestClient.bindToController(bookmarkRepository).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listMyBookmarks() {
    }

    @Test
    void listMySharedBookmarks() {
    }

    @Test
    void listOtherBookmarks() {
    }

    @Test
    void listSharedBookmarks() {
    }

    @Test
    void getById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }
}