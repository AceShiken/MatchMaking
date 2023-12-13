package com.chitchat.matchmaking.repository;

import com.chitchat.matchmaking.models.ParticipationInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationInfoRepository extends MongoRepository<ParticipationInfo, Integer>,
        CrudRepository<ParticipationInfo, Integer>,
        PagingAndSortingRepository<ParticipationInfo, Integer>,
        QueryByExampleExecutor<ParticipationInfo> {
}
