package com.chitchat.matchmaking.repository;

import com.chitchat.matchmaking.models.UserRewards;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRewardsRepository extends MongoRepository<UserRewards, Integer>,
        CrudRepository<UserRewards, Integer>,
        PagingAndSortingRepository<UserRewards, Integer>,
        QueryByExampleExecutor<UserRewards> {
}
