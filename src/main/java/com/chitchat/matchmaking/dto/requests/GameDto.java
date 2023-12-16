package com.chitchat.matchmaking.dto.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("game_name")
    private String gameName;
    @JsonProperty("is_battle")
    private boolean isBattle;
    @JsonProperty("is_tournament")
    private boolean isTournament;
    @JsonProperty("available_countries")
    private List<String> availableCountries;
    @JsonProperty("game_icon")
    private String gameIcon;
    @JsonProperty("extra_info")
    private String extraInfo;
}
