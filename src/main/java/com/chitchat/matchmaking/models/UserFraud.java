package com.chitchat.matchmaking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_fraud")
public class UserFraud {
    @Indexed
    @MongoId
    private int id;
    @Field(name = "user_id")
    private int userId;
    @Field(name = "intensity")
    private int intensity;
    @Field(name = "uplift_time")
    private Date upliftTime;

    public UserFraud(int userId, int intensity, Date upliftTime) {
        this.userId = userId;
        this.intensity = intensity;
        this.upliftTime = upliftTime;
    }
}
