package com.chitchat.matchmaking.logic;

import com.chitchat.matchmaking.models.Battle;
import com.chitchat.matchmaking.models.Game;
import com.chitchat.matchmaking.models.UserRewards;
import com.chitchat.matchmaking.repository.BattleRepository;
import com.chitchat.matchmaking.repository.GameRepository;
import com.chitchat.matchmaking.repository.UserRepository;
import com.chitchat.matchmaking.repository.UserRewardsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class ScoreLogic {

    /*
    Inputs
    RTP : Return to Player (RTP) is a term used in gambling and online games to refer to the percentage or prizes
        that will be returned to a player depending on funds deposited during the game initially.
        Wiki - https://en.wikipedia.org/wiki/Return_to_Player
    Skill :  All Games where the success and/or performance of the Users depends predominantly upon their
        superior knowledge, training, attention, experience and physical skill (like fast reaction or dexterity)
        in playing the game.
        Wiki - https://en.wikipedia.org/wiki/Online_skill-based_game
     */

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRewardsRepository rewardsRepository;

    @Autowired
    private BattleRepository battleRepository;

    public double getUserRTP(int days, int userId, int gameId) {
        Date todayMinusDays = new Date(System.currentTimeMillis()- (long) days *24*60*60*1000);
        List<Battle> battles = battleRepository.getAllBattlesByGameIdAfter(gameId, todayMinusDays);
        if(battles.isEmpty()) {
            return 0d;
        }
        double winnings = 0d;
        double spends = 0d;
        for (Battle battle : battles) {
            int battleId = battle.getId();
            Optional<UserRewards> optionalRewards = rewardsRepository.getRewardsByUserIdAndBattleId(userId, battleId);
            if(!optionalRewards.isPresent()) {
                return 0d;
            }
            UserRewards rewards = optionalRewards.get();
            winnings += rewards.getWinnings();
            spends += battle.getBuyIn() + battle.getEntryFees() + battle.getFixedRake();
        }
        if(spends==0) return 0d;
        return winnings/spends;
    }

    public double getUserRTP(int days, int userId, int gameId, String countryCode) {
        Date todayMinusDays = new Date(System.currentTimeMillis()- (long) days *24*60*60*1000);
        List<Battle> battles = battleRepository.getAllBattlesByGameIdAfter(gameId, todayMinusDays);
        if(battles.isEmpty()) {
            return 0d;
        }
        double winnings = 0d;
        double spends = 0d;
        for (Battle battle : battles) {
            if(battle.getCountries().contains(countryCode)) {
                int battleId = battle.getId();
                Optional<UserRewards> optionalRewards = rewardsRepository.getRewardsByUserIdAndBattleId(userId, battleId);
                if (!optionalRewards.isPresent()) {
                    return 0d;
                }
                UserRewards rewards = optionalRewards.get();
                winnings += rewards.getWinnings();
                spends += battle.getBuyIn() + battle.getEntryFees() + battle.getFixedRake();
            }
        }
        if(spends==0) return 0d;
        return winnings/spends;
    }

    public double getUserRTP(int userId, int gameId, String countryCode) {
        List<Battle> battles = battleRepository.getAllBattlesByGameId(gameId);
        if(battles.isEmpty()) {
            return 0d;
        }
        double winnings = 0d;
        double spends = 0d;
        for (Battle battle : battles) {
            if(battle.getCountries().contains(countryCode)) {
                int battleId = battle.getId();
                Optional<UserRewards> optionalRewards = rewardsRepository.getRewardsByUserIdAndBattleId(userId, battleId);
                if (!optionalRewards.isPresent()) {
                    return 0d;
                }
                UserRewards rewards = optionalRewards.get();
                winnings += rewards.getWinnings();
                spends += battle.getBuyIn() + battle.getEntryFees() + battle.getFixedRake();
            }
        }
        if(spends==0) return 0d;
        return winnings/spends;
    }

    public double getUserRTP(int userId, int gameId) {
        List<Battle> battles = battleRepository.getAllBattlesByGameId(gameId);
        if(battles.isEmpty()) {
            return 0d;
        }
        double winnings = 0d;
        double spends = 0d;
        for (Battle battle : battles) {
            int battleId = battle.getId();
            Optional<UserRewards> optionalRewards = rewardsRepository.getRewardsByUserIdAndBattleId(userId, battleId);
            if (!optionalRewards.isPresent()) {
                return 0d;
            }
            UserRewards rewards = optionalRewards.get();
            winnings += rewards.getWinnings();
            spends += battle.getBuyIn() + battle.getEntryFees() + battle.getFixedRake();
        }
        if(spends==0) return 0d;
        return winnings/spends;
    }
}
