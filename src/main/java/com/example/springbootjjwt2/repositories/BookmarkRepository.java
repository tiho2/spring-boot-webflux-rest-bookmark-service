package com.example.springbootjjwt2.repositories;

import com.example.springbootjjwt2.model.Bookmark;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface BookmarkRepository extends ReactiveMongoRepository<Bookmark, String> {

}
