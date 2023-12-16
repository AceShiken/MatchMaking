package com.chitchat.matchmaking.dto.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUserRewardsRequest {
    @JsonProperty("score")
    private double score;
    @JsonProperty("winnings")
    private double winnings;
    @JsonProperty("rank")
    private int rank;
}
