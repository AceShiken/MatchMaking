package com.chitchat.matchmaking.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FraudIntensity {
    NONE(0),
    RISKY(1),
    VERY_RISKY(2),
    BLOCK(3);

    private final int intensityLevel;

    FraudIntensity(int intensityLevel) {
        this.intensityLevel = intensityLevel;
    }

}
