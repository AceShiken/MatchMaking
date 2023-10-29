package com.chitchat.matchmaking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRewards {
    private int id;
    private int userId;
    private int battleId;
    private double score;
    private double winnings;
    private int rank;
}
