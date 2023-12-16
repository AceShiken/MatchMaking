package com.chitchat.matchmaking.dto.requests;

import lombok.Data;

@Data
public class FraudIntensityRequest {
    private int intensity;
    private int timeInHours;
}
