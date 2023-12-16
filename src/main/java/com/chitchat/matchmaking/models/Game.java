package com.chitchat.matchmaking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "game")
public class Game {
    @Indexed
    @MongoId
    private int id;
    @Field(name = "game_name")
    private String gameName;
    @Field(name = "is_battle")
    private boolean isBattle;
    @Field(name = "is_tournament")
    private boolean isTournament;
    @Field(name = "available_countries")
    private List<String> availableCountries;
    @Field(name = "game_icon")
    private String gameIcon;
    @Field(name = "extra_info")
    private String extraInfo;
}
