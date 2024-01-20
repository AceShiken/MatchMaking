package com.chitchat.matchmaking.dto.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JoinBattleRequest {
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("gameId")
    private int gameId;
    @JsonProperty("battleId")
    private int battleId;
}
