package com.chitchat.matchmaking.repository;

import com.chitchat.matchmaking.models.UserFraud;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFraudRepository extends MongoRepository<UserFraud, Integer>,
        CrudRepository<UserFraud, Integer>,
        PagingAndSortingRepository<UserFraud, Integer>,
        QueryByExampleExecutor<UserFraud> {
}
