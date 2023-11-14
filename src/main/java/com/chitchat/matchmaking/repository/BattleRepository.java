package com.chitchat.matchmaking.repository;

import com.chitchat.matchmaking.models.Battle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleRepository extends MongoRepository<Battle, Integer>{
}
