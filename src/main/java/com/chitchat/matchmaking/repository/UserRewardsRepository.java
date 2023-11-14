package com.chitchat.matchmaking.repository;

import com.chitchat.matchmaking.models.UserRewards;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRewardsRepository extends MongoRepository<UserRewards, Integer> {
}
