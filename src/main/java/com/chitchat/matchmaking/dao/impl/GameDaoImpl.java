package com.chitchat.matchmaking.dao.impl;

import com.chitchat.matchmaking.dao.GameDao;
import com.chitchat.matchmaking.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GameDaoImpl implements GameDao {

    @Autowired
    private GameRepository gameRepository;
}
