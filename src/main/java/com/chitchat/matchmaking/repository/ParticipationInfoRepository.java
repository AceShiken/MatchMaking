package com.chitchat.matchmaking.repository;

import com.chitchat.matchmaking.models.ParticipationInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ParticipationInfoRepository extends MongoRepository<ParticipationInfo, Integer>,
        CrudRepository<ParticipationInfo, Integer>,
        PagingAndSortingRepository<ParticipationInfo, Integer>,
        QueryByExampleExecutor<ParticipationInfo> {
    @Query("{battle_id=?0}")
    Optional<ParticipationInfo> getParticipationInfoByBattleId(int battleId);

}
