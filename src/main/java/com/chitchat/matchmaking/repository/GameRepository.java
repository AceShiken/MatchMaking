package com.chitchat.matchmaking.repository;

import com.chitchat.matchmaking.models.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

public interface GameRepository extends MongoRepository<Game, Integer>,
        CrudRepository<Game, Integer>,
        PagingAndSortingRepository<Game, Integer>,
        QueryByExampleExecutor<Game> {
}
