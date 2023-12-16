package com.chitchat.matchmaking.service;

import com.chitchat.matchmaking.dao.BattleDao;
import com.chitchat.matchmaking.exceptions.InvalidRequestException;
import com.chitchat.matchmaking.models.Battle;
import com.chitchat.matchmaking.models.ParticipationInfo;
import com.chitchat.matchmaking.repository.BattleRepository;
import com.chitchat.matchmaking.repository.ParticipationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BattleService {

    @Autowired
    private BattleDao battleDao;

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private ParticipationInfoRepository participationInfoRepository;

    public Battle getBattle(int id) throws InvalidRequestException {
        Optional<Battle> optionalBattle = battleRepository.findById(id);
        if(!optionalBattle.isPresent()) {
            throw new InvalidRequestException("Invalid Battle Id");
        }
        return optionalBattle.get();
    }

    public List<Battle> getAllBattles(int gameId) {
        return battleRepository.getAllBattlesByGameId(gameId);
    }

    public ParticipationInfo getBattleParticipationInfo(int battleId) throws InvalidRequestException {
        Optional<ParticipationInfo> optionalParticipationInfo =
                participationInfoRepository.getParticipationInfoByBattleId(battleId);
        if(!optionalParticipationInfo.isPresent()) {
            throw new InvalidRequestException("Invalid Battle Id");
        }
        return optionalParticipationInfo.get();
    }

    public Battle createBattle(Battle battle) {
        return battleRepository.insert(battle);
    }

    public Battle updateBattle(Battle battle) {
        return battleRepository.save(battle);
    }
}
