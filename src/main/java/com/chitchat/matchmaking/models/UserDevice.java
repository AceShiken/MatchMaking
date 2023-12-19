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
@Document
public class UserDevice {
    @Indexed
    @MongoId
    private int id;
    @Field(name = "user_id")
    private int userId;
    @Field(name = "imei")
    private String imei;
    @Field(name = "ram")
    private Integer ramInGB;
    @Field(name = "os")
    private String OS;
    @Field(name = "os_version")
    private String OSVersion;

    public UserDevice(int userId, String imei, Integer ramInGB, String OS, String OSVersion) {
        this.userId = userId;
        this.imei = imei;
        this.ramInGB = ramInGB;
        this.OS = OS;
        this.OSVersion = OSVersion;
    }
}
