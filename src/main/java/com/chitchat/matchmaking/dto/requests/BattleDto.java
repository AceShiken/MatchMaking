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
    @JsonProperty("is_1v1")
    private boolean is1v1;
    @JsonProperty("is_cross_geo_enabled")
    private boolean isCrossGeoEnabled;
    @JsonProperty("entry_fees")
    private double entryFees;
    @JsonProperty("entry_currency")
    private String entryCurrency;
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
    @JsonProperty("eligible_x_min_lobby")
    private double eligibleMinCrossLobby;
    @JsonProperty("eligible_x_max_lobby")
    private double eligibleMaxCrossLobby;
}
