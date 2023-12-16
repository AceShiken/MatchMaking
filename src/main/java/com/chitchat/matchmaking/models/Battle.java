package com.chitchat.matchmaking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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
    @Id
    private int id;
    @Field(name = "name")
    private String name;
    @Field(name = "is_1v1")
    private boolean is1v1;
    @Field(name = "is_cross_lobby_enabled")
    private boolean isCrossLobbyEnabled;
    @Field(name = "is_cross_geo_enabled")
    private boolean isCrossGeoEnabled;
    @Field(name = "entry_fees")
    private double entryFees;
    @Field(name = "entry_currency")
    private String entryCurrency;
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
    @Field(name = "eligible_x_min_lobby")
    private double eligibleMinCrossLobby;
    @Field(name = "eligible_x_max_lobby")
    private double eligibleMaxCrossLobby;
}
