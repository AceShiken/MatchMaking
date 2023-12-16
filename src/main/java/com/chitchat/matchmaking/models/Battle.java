package com.chitchat.matchmaking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "battle")
public class Battle {
    @Indexed
    @MongoId
    private int id;
    @Field(name = "name")
    private String name;
    @Field(name = "is_tournament")
    private boolean isTournament;
    @Field(name = "is_cross_lobby_enabled")
    private boolean isCrossLobbyEnabled;
    @Field(name = "game_id")
    private int gameId;
    @Field(name = "buy_in")
    private double buyIn;
    @Field(name = "fixed_rake")
    private double fixedRake;
    @Field(name = "reward_info")
    private String rewardInfo;
    @Field(name = "countries")
    private List<String> countries;
    @Field(name = "icon")
    private String icon;
    @Field(name = "start_time")
    private Date startTime;
    @Field(name = "end_time")
    private Date endTime;
    @Field(name = "ranked_rewards")
    private List<UserRewards> rankedRewards;
}
