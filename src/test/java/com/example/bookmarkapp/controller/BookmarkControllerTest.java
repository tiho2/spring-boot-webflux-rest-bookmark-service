package com.example.bookmarkapp.controller;

import com.example.bookmarkapp.model.Bookmark;
import com.example.bookmarkapp.repositories.BookmarkRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@SpringBootTest
class BookmarkControllerTest {

    WebTestClient webTestClient;
    BookmarkRepository bookmarkRepository;
    BookmarkController bookmarkController;

    @BeforeEach
    void setUp() {
        bookmarkRepository = Mockito.mock(BookmarkRepository.class);
        bookmarkController = new BookmarkController(bookmarkRepository);
        webTestClient = WebTestClient.bindToController(bookmarkController).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listBookmarks() {
        BDDMockito.given(bookmarkRepository.findAll())
                .willReturn(Flux.just(Bookmark.builder().url("http://spring.io").owner("user").shared(Boolean.TRUE).build(),
                        Bookmark.builder().url("http://docs.spring.io").owner("user").shared(Boolean.FALSE).build()));

        webTestClient.get().uri("http://localhost:8080/bookmarks?scope=&scopeDetails=")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBodyList(Bookmark.class);
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


    @Test
    void testGetById() {
    }

    @Test
    void patchBookmark() {
    }

    @Test
    void testUpdate() {
    }
}