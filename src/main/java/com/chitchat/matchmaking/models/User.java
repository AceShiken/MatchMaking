package com.chitchat.matchmaking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {
    @Indexed
    @MongoId
    private int id;
    @Field(name = "first_name")
    private String firstName;
    @Field(name = "last_name")
    private String lastName;
    @Field(name = "display_name")
    private String displayName;
    @Field(name = "is_affiliate")
    private boolean isAffiliate;
    @Field(name = "country")
    private String country;
    @Field(name = "additive_boost")
    private double additiveBoost;
    @Field(name = "multiplier_boost")
    private double multiplierBoost;
}
