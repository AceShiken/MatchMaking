package com.chitchat.matchmaking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    private int id;
    private String gameName;
    private boolean isBattle;
    private boolean isTournament;
    private List<String> availableCountries;
    private String gameIcon;
    private String extraInfo;
}
