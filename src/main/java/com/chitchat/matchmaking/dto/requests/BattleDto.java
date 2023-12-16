package com.chitchat.matchmaking.dto.requests;

import com.chitchat.matchmaking.models.UserRewards;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BattleDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("is_tournament")
    private boolean isTournament;
    @JsonProperty("is_cross_lobby_enabled")
    private boolean isCrossLobbyEnabled;
    @JsonProperty("game_id")
    private int gameId;
    @JsonProperty("buy_in")
    private double buyIn;
    @JsonProperty("fixed_rake")
    private double fixedRake;
    @JsonProperty("reward_info")
    private String rewardInfo;
    @JsonProperty("countries")
    private List<String> countries;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("start_time")
    private Date startTime;
    @JsonProperty("end_time")
    private Date endTime;
    @JsonProperty("ranked_rewards")
    private List<UserRewards> rankedRewards;
}
