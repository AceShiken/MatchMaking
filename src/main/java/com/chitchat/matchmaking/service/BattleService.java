package com.chitchat.matchmaking.service;

import com.chitchat.matchmaking.dto.requests.BattleDto;
import com.chitchat.matchmaking.dto.requests.JoinBattleRequest;
import com.chitchat.matchmaking.dto.response.JoinBattleResponse;
import com.chitchat.matchmaking.enums.FraudIntensity;
import com.chitchat.matchmaking.exceptions.InvalidRequestException;
import com.chitchat.matchmaking.exceptions.InvalidUserException;
import com.chitchat.matchmaking.mapper.DtoToEntityMapper;
import com.chitchat.matchmaking.models.Battle;
import com.chitchat.matchmaking.models.ParticipationInfo;
import com.chitchat.matchmaking.models.User;
import com.chitchat.matchmaking.models.UserFraud;
import com.chitchat.matchmaking.repository.BattleRepository;
import com.chitchat.matchmaking.repository.ParticipationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.chitchat.matchmaking.utils.Constants.*;

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

    public JoinBattleResponse joinBattle(JoinBattleRequest joinBattleRequest) throws InvalidRequestException, InvalidUserException {
        long currentTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis() + CUT_OFF_TIME_IN_MINUTES*60*1000;
        int battleId = joinBattleRequest.getBattleId();
        int userId = joinBattleRequest.getUserId();
        Battle battle = getBattle(battleId);
        if(new Date(currentTime).after(battle.getEndTime())) {
            throw new InvalidRequestException("Battle has already ended");
        }
        User user = userService.getUser(userId);
        if(BANNED_STATES.contains(user.getState())) {
            throw new InvalidRequestException("User participating from banned state");
        }
        UserFraud userFraud = userService.getUserFraudInfo(userId);
        if(FraudIntensity.BLOCK.equals(userFraud.getIntensity())) {
            throw new InvalidUserException("Blocked User");
        }
        Optional<ParticipationInfo> participationInfoOptional = participationInfoRepository.getParticipationInfoByBattleId(battleId);
        List<Integer> registeredUsers = new ArrayList<>();
        registeredUsers.add(userId);
        int participationId;
        if(!participationInfoOptional.isPresent()) {
            ParticipationInfo participationInfo = new ParticipationInfo();
            participationInfo.setBattleId(battleId);
            participationInfo.setRegisteredUsers(registeredUsers);
            participationInfo.setRegistrationStartTime(new Date(currentTime));
            participationInfo.setRegistrationStartTime(new Date(endTime));
            participationId = participationInfoRepository.insert(participationInfo).getId();
        } else {
            ParticipationInfo participationInfo = participationInfoOptional.get();
            if(participationInfo.getRegisteredUsers().size() == CUT_OFF_USERS) {
                ParticipationInfo newParticipationInfo = new ParticipationInfo();
                newParticipationInfo.setBattleId(battleId);
                newParticipationInfo.setRegisteredUsers(registeredUsers);
                newParticipationInfo.setRegistrationStartTime(new Date(currentTime));
                newParticipationInfo.setRegistrationStartTime(new Date(endTime));
                participationInfoRepository.insert(newParticipationInfo);
                participationId = participationInfoRepository.insert(participationInfo).getId();
            } else {
                registeredUsers.addAll(participationInfo.getRegisteredUsers());
                participationInfo.setRegisteredUsers(registeredUsers);
                participationInfoRepository.save(participationInfo);
                participationId = participationInfo.getId();
            }
        }
        return new JoinBattleResponse(true, participationId);
    }
}
