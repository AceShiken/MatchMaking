package com.chitchat.matchmaking.repository;

import com.chitchat.matchmaking.models.UserFraud;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserFraudRepository extends MongoRepository<UserFraud, Integer>,
        CrudRepository<UserFraud, Integer>,
        PagingAndSortingRepository<UserFraud, Integer>,
        QueryByExampleExecutor<UserFraud> {
    @Query("{user_id=?0}")
    Optional<UserFraud> getFraudByUserId(int userId);
}
