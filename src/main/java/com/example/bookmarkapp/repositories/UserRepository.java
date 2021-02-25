package com.example.bookmarkapp.repositories;

import com.example.bookmarkapp.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends ReactiveMongoRepository <User, String> {
}
