package com.example.bookmarkapp.controller;

import com.example.bookmarkapp.model.Bookmark;
import com.example.bookmarkapp.repositories.BookmarkRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class BookmarkController {

    private final BookmarkRepository bookmarkRepository;
    UrlValidator urlValidator = new UrlValidator();

    public BookmarkController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }


    @GetMapping(value = "/api/v1/bookmarks")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasRole('USER')")
    Flux<Bookmark> listBookmarks(Principal principal,
                                 @RequestParam String scope,
                                 @RequestParam String scopeDetail) {
        final String username = principal.getName();
        switch (bookmarkListType(scope, scopeDetail)) {
            case SHARED_ALL:
                return this.bookmarkRepository.findAll(Example.of(Bookmark.builder().shared(Boolean.TRUE).build())).limitRate(40, 5);
            case SHARED_OTHER:
                // TODO: make an improvement to avoid need for filter()
                return this.bookmarkRepository.findAll(Example.of(Bookmark.builder().shared(Boolean.TRUE).build())).filter(b -> (!b.getOwner().equals(username))).limitRate(40, 5);
            case USER_PUBLIC:
                return this.bookmarkRepository.findAll(Example.of(Bookmark.builder().shared(Boolean.TRUE).owner(username).build())).limitRate(40, 5);
            case USER_PRIVATE:
                return this.bookmarkRepository.findAll(Example.of(Bookmark.builder().shared(Boolean.FALSE).owner(username).build())).limitRate(40, 5);
            default:
                //is USER_ALL case
                return this.bookmarkRepository.findAll(Example.of(Bookmark.builder().owner(username).build())).limitRate(40, 5);
        }

    }


    @GetMapping("api/v1/bookmarks/{id}")
    Mono<Bookmark> getById(@PathVariable String id) {
        return bookmarkRepository.findById(id);
    }

    @PatchMapping("api/v1/bookmarks/{id}/{shared}")
    @PreAuthorize("hasRole('USER')")
    Mono<Bookmark> patchBookmark(Principal principal, @PathVariable String id, @PathVariable Boolean shared) {
        Bookmark b = bookmarkRepository.findById(id).block();
        if (b != null && b.getOwner().equalsIgnoreCase(principal.getName())) {
            b.setShared(shared);
            return bookmarkRepository.save(b);
        }
        return Mono.empty();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("api/v1/bookmarks/")
    @PreAuthorize("hasRole('USER')")
    Mono<Bookmark> update(@RequestBody Bookmark bookmark, Principal principal) {
        if (urlValidator.isValid(bookmark.getUrl())) {
            return bookmarkRepository.save(Bookmark.builder().url(bookmark.getUrl()).shared(bookmark.getShared()).owner(principal.getName()).build());
        } else {
            return Mono.empty();
        }
    }

    private BookmarkListType bookmarkListType(String scope, String details) {

        scope = scope.trim().toLowerCase();
        details = details.trim().toLowerCase();

        switch (scope) {
            case "shared":
                if ("other".equals(details)) {
                    return BookmarkListType.SHARED_OTHER;
                }
                return BookmarkListType.SHARED_ALL;
            case "user":
                switch (details) {
                    case "public":
                        return BookmarkListType.USER_PUBLIC;
                    case "private":
                        return BookmarkListType.USER_PRIVATE;
                }
            default:
                return BookmarkListType.USER_ALL;

        }
    }

    private enum BookmarkListType {
        USER_ALL, USER_PRIVATE, USER_PUBLIC, SHARED_ALL, SHARED_OTHER
    }

}



