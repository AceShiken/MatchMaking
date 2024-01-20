package com.chitchat.matchmaking.service;

import com.chitchat.matchmaking.dto.requests.BattleDto;
import com.chitchat.matchmaking.dto.requests.JoinBattleRequest;
import com.chitchat.matchmaking.dto.response.JoinBattleResponse;
import com.chitchat.matchmaking.exceptions.InvalidRequestException;
import com.chitchat.matchmaking.mapper.DtoToEntityMapper;
import com.chitchat.matchmaking.models.Battle;
import com.chitchat.matchmaking.models.ParticipationInfo;
import com.chitchat.matchmaking.models.User;
import com.chitchat.matchmaking.repository.BattleRepository;
import com.chitchat.matchmaking.repository.ParticipationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BattleService {

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ParticipationInfoRepository participationInfoRepository;

    @Autowired
    private DtoToEntityMapper dtoToEntityMapper;

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

    public Battle createBattle(BattleDto battle) {
        return battleRepository.insert(dtoToEntityMapper.convertToEntity(battle));
    }

    public Battle updateBattle(BattleDto battle) {
        return battleRepository.save(dtoToEntityMapper.convertToEntity(battle));
    }

    public JoinBattleResponse joinBattle(JoinBattleRequest joinBattleRequest) {
        long currentTime = System.currentTimeMillis();
        int battleId = joinBattleRequest.getBattleId();
        int userId = joinBattleRequest.getUserId();
        try {
            Battle battle = getBattle(battleId);
            User user = userService.getUser(userId);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
        return null;
    }
}
