package com.chitchat.matchmaking.repository;

import com.chitchat.matchmaking.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

public interface UserRepository extends MongoRepository<User, Integer>,
        CrudRepository<User, Integer>,
        PagingAndSortingRepository<User, Integer>,
        QueryByExampleExecutor<User> {
}
