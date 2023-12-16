package com.chitchat.matchmaking.repository;

import com.chitchat.matchmaking.models.Battle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BattleRepository extends MongoRepository<Battle, Integer>,
        CrudRepository<Battle, Integer>,
        PagingAndSortingRepository<Battle, Integer>,
        QueryByExampleExecutor<Battle> {
    @Query("{game_id=?0}")
    List<Battle> getAllBattlesByGameId(int gameId);
}
