package com.chitchat.matchmaking.controller;

import com.chitchat.matchmaking.dto.requests.JoinRequest;
import com.chitchat.matchmaking.dto.response.JoinResponse;
import com.chitchat.matchmaking.service.MatchMakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
@RestController("/api/mm")
public class MatchMakingController {

    @Autowired
    private MatchMakingService mmService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JoinResponse joinTable(@RequestBody JoinRequest joinRequest) {
        return null;
    }
}
