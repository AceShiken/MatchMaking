package com.chitchat.matchmaking.dto.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Date;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("external_id")
    private String externalId;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("dob")
    private Date dob;
    @JsonProperty("is_affiliate")
    private boolean isAffiliate;
    @JsonProperty("state")
    private String state;
    @JsonProperty("country")
    private String country;
    @JsonProperty("additive_boost")
    private double additiveBoost;
    @JsonProperty("multiplier_boost")
    private double multiplierBoost;
    @JsonProperty("is_cross_lobby_eligible")
    private boolean isCrossLobbyEligible;
    @JsonProperty("is_cross_geo_eligible")
    private boolean isCrossGeoEligible;
}
