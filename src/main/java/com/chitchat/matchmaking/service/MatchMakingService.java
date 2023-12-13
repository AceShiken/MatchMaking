package com.chitchat.matchmaking.service;

import com.chitchat.matchmaking.dao.BattleDao;
import com.chitchat.matchmaking.exceptions.InvalidRequestException;
import com.chitchat.matchmaking.models.Battle;
import com.chitchat.matchmaking.repository.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchMakingService {

    @Autowired
    private BattleDao battleDao;

    @Autowired
    private BattleRepository battleRepository;

    public Battle getBattle(int id) throws InvalidRequestException {
        Optional<Battle> optionalBattle = battleRepository.findById(id);
        if(!optionalBattle.isPresent()) {
            throw new InvalidRequestException("Invalid Battle Id");
        }
        return optionalBattle.get();
    }

    public List<Battle> getAllBattles(int gameId) {
        return battleDao.getAllBattlesByGameId(gameId);
    }
}
