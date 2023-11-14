package com.chitchat.matchmaking.repository;

import com.chitchat.matchmaking.models.UserFraud;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFraudRepository extends MongoRepository<UserFraud, Integer> {
}
