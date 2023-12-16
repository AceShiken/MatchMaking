package com.chitchat.matchmaking.service;

import com.chitchat.matchmaking.dto.requests.FraudIntensityRequest;
import com.chitchat.matchmaking.dto.requests.UpdateUserRewardsRequest;
import com.chitchat.matchmaking.exceptions.InvalidRequestException;
import com.chitchat.matchmaking.models.User;
import com.chitchat.matchmaking.models.UserFraud;
import com.chitchat.matchmaking.models.UserRewards;
import com.chitchat.matchmaking.repository.UserFraudRepository;
import com.chitchat.matchmaking.repository.UserRepository;
import com.chitchat.matchmaking.repository.UserRewardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFraudRepository fraudRepository;

    @Autowired
    private UserRewardsRepository rewardsRepository;

    public User getUser(int id) throws InvalidRequestException {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()) {
            throw new InvalidRequestException("Invalid User Id");
        }
        return optionalUser.get();
    }

    public UserFraud getUserFraudInfo(int userId) throws InvalidRequestException {
        Optional<UserFraud> optionalUserFraud = fraudRepository.getFraudByUserId(userId);
        if(!optionalUserFraud.isPresent()) {
            throw new InvalidRequestException("Invalid User Id");
        }
        return optionalUserFraud.get();
    }

    public UserRewards getUserRewards(int userId, int battleId) throws InvalidRequestException {
        Optional<UserRewards> optionalUserRewards =
                rewardsRepository.getRewardsByUserIdAndBattleId(userId, battleId);
        if(!optionalUserRewards.isPresent()) {
            throw new InvalidRequestException("Invalid User Id and Battle Id");
        }
        return optionalUserRewards.get();
    }

    public UserFraud enterFraud(int userId, FraudIntensityRequest fraudIntensityRequest) {
        return fraudRepository.insert(new UserFraud(userId, fraudIntensityRequest.getIntensity(),
                new Date(System.currentTimeMillis() + (long) fraudIntensityRequest.getTimeInHours() *60*60*1000)));
    }

    public UserRewards updateUserRewards(int userId, int battleId, UpdateUserRewardsRequest userRewards) throws InvalidRequestException {
        UserRewards userRewardsFromDB = getUserRewards(userId, battleId);
        userRewardsFromDB.setRank(userRewards.getRank());
        userRewardsFromDB.setScore(userRewards.getScore());
        userRewardsFromDB.setWinnings(userRewards.getWinnings());
        return rewardsRepository.save(userRewardsFromDB);
    }
}
