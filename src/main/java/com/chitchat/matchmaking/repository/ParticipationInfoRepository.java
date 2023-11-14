package com.chitchat.matchmaking.repository;

import com.chitchat.matchmaking.models.ParticipationInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationInfoRepository extends MongoRepository<ParticipationInfo, Integer> {
}
