package com.example.bookmarkapp.controller;

import com.example.bookmarkapp.model.Bookmark;
import com.example.bookmarkapp.repositories.BookmarkRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class BookmarkController {

    private BookmarkRepository bookmarkRepository;
    UrlValidator urlValidator = new UrlValidator();

    public BookmarkController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }
    @RequestMapping(value = "/api/v1/bookmarks/user", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasRole('USER')")
    Flux<Bookmark> listMyBookmarks(Principal principal) {
        return this.bookmarkRepository.findAll().filter(b -> b.getOwner().equals(principal.getName()));
    }

    @RequestMapping(value = "/api/v1/bookmarks/user/shared", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasRole('USER')")
    Flux<Bookmark> listMySharedBookmarks(Principal principal) {
        return this.bookmarkRepository.findAll().filter(b -> (b.getOwner().equals(principal.getName())) && b.getShared().equals(Boolean.TRUE));
    }

    @RequestMapping(value = "/api/v1/bookmarks/other", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasRole('USER')")
    Flux<Bookmark> listOtherBookmarks(Principal principal) {
        return this.bookmarkRepository.findAll().filter(b -> (!b.getOwner().equals(principal.getName())) && b.getShared().equals(Boolean.TRUE));
    }

    @RequestMapping(value = "/api/v1/bookmarks", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasRole('USER')")
    Flux<Bookmark> listSharedBookmarks(Principal principal) {
        return this.bookmarkRepository.findAll().filter(b -> b.getShared().equals(Boolean.TRUE));
    }

    @GetMapping("api/v1/bookmarks/{id}")
    Mono<Bookmark> getById(@PathVariable String id){
        return bookmarkRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("api/v1/bookmarks")
    @PreAuthorize("hasRole('USER')")
    Mono<Bookmark> create (@RequestBody Bookmark bookmark, Principal principal){
        if(urlValidator.isValid(bookmark.getUrl())){
            return bookmarkRepository.save( Bookmark.builder().url(bookmark.getUrl()).shared(bookmark.getShared()).owner(principal.getName()).build());
        }
        else return Mono.empty();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("api/v1/bookmarks/{id}")
    @PreAuthorize("hasRole('USER')")
    Mono<Bookmark> update (@PathVariable String id, @RequestBody Bookmark bookmark, Principal principal){
        if (principal.getName().equals(bookmark.getOwner())) {

            return bookmarkRepository.save( Bookmark.builder().id(id).url(bookmark.getUrl()).shared(bookmark.getShared()).owner(principal.getName()).build());
        } else
            return Mono.error(new Exception(String.format("Logged user with username '%s' doesn't own the bookmark. Update not allowed.", principal.getName())));
    }
}
