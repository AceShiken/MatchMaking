package com.chitchat.matchmaking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinBattleResponse {
    private boolean hasJoined;
    private int participationId;
}
