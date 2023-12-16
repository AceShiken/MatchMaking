package com.chitchat.matchmaking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {
    @Indexed
    @Id
    private int id;
    @Field(name = "external_id")
    private String externalId;
    @Field(name = "first_name")
    private String firstName;
    @Field(name = "last_name")
    private String lastName;
    @Field(name = "display_name")
    private String displayName;
    @Field(name = "dob")
    private Date dob;
    @Field(name = "is_affiliate")
    private boolean isAffiliate;
    @Field(name = "state")
    private String state;
    @Field(name = "country")
    private String country;
    @Field(name = "additive_boost")
    private double additiveBoost;
    @Field(name = "multiplier_boost")
    private double multiplierBoost;
    @Field(name = "is_cross_lobby_eligible")
    private boolean isCrossLobbyEligible;
    @Field(name = "is_cross_geo_eligible")
    private boolean isCrossGeoEligible;
}
