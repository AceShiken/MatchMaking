package com.chitchat.matchmaking.dto.requests;

import com.chitchat.matchmaking.enums.FraudIntensity;
import lombok.Data;

@Data
public class FraudIntensityRequest {
    private FraudIntensity intensity;
    private int timeInHours;
}
