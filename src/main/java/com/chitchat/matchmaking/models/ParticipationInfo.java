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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "participation_info")
public class ParticipationInfo {
    @Indexed
    @Id
    private int id;
    @Field(name = "registered_users")
    private List<String> registeredUsers;
    @Field(name = "battle_id")
    private String battleId;
    @Field(name = "registration_start_time")
    private Date registrationStartTime;
    @Field(name = "registration_end_time")
    private Date registrationEndTime;
}
