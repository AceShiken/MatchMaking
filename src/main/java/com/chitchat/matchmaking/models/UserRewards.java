package com.chitchat.matchmaking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_rewards")
public class UserRewards {
    @Indexed
    @Id
    private int id;
    @Field(name = "user_id")
    private int userId;
    @Field(name = "battle_id")
    private int battleId;
    @Field(name = "score")
    private double score;
    @Field(name = "winnings")
    private double winnings;
    @Field(name = "rank")
    private int rank;
}
