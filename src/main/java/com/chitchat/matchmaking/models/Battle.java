package com.chitchat.matchmaking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Battle {
    @Id
    private int id;
    private String name;
    private boolean isTournament;
    private boolean isCrossLobbyEnabled;
    private int gameId;
    private double buyIn;
    private double fixedRake;
    private String rewardInfo;
    private List<String> countries;
    private String icon;
    private Date startTime;
    private Date endTime;
    private List<UserRewards> rankedRewards;
}
