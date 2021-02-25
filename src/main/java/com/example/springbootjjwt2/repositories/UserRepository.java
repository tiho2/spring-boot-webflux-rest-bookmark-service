package com.example.springbootjjwt2.repositories;

import com.example.springbootjjwt2.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends ReactiveMongoRepository <User, String> {
}
