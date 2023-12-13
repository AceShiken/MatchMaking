package com.chitchat.matchmaking.dao;

import com.chitchat.matchmaking.models.Battle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BattleDao {
    List<Battle> getAllBattlesByGameId(int gameId);
}
