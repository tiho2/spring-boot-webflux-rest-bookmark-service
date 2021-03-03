package com.example.bookmarkapp.repositories;

import com.example.bookmarkapp.model.Bookmark;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface BookmarkRepository extends ReactiveMongoRepository<Bookmark, String> {

}
