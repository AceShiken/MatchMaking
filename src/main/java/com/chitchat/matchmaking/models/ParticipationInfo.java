package com.chitchat.matchmaking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationInfo {
    @Id
    private int id;
    private List<String> registeredUsers;
    private String battleId;
    private Date registrationStartTime;
    private Date registrationEndTime;
}
