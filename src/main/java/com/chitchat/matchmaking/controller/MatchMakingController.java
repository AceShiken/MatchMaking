package com.chitchat.matchmaking.controller;

import com.chitchat.matchmaking.exceptions.InvalidRequestException;
import com.chitchat.matchmaking.models.*;
import com.chitchat.matchmaking.service.MatchMakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/mm")
public class MatchMakingController {

    @Autowired
    private MatchMakingService mmService;

    @GetMapping(value = "/battles/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Battle getBattle(HttpHeaders headers,
                            @PathVariable int id) throws InvalidRequestException {
        String countryCode = headers.getFirst("countryCode");
        return mmService.getBattle(id);
    }

    @GetMapping(value = "/battles/all/{gameId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Battle> getAllBattles(HttpHeaders headers,
                                  @PathVariable int gameId) {
        String countryCode = headers.getFirst("countryCode");
        return mmService.getAllBattles(gameId);
    }

    @GetMapping(value = "/battles/{id}/participation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ParticipationInfo getBattleParticipationInfo(HttpHeaders headers,
                                                        @PathVariable String id) {
        String countryCode = headers.getFirst("countryCode");
        return null;
    }

    @PostMapping(value = "/battles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Battle createBattle(HttpHeaders headers, @RequestBody Battle battle) {
        String countryCode = headers.getFirst("countryCode");
        return null;
    }

    @PostMapping(value = "/battles/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Battle updateBattle(HttpHeaders headers, @RequestBody Battle battle) {
        String countryCode = headers.getFirst("countryCode");
        return null;
    }

    @GetMapping(value = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(HttpHeaders headers,
                        @PathVariable String id) {
        String countryCode = headers.getFirst("countryCode");
        return null;
    }

    @GetMapping(value = "/user/{id}/fraudInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserFraud getUserFraudInfo(HttpHeaders headers,
                                      @PathVariable String id) {
        String countryCode = headers.getFirst("countryCode");
        return null;
    }

    @GetMapping(value = "/user/{id}/rewards/{battleId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRewards getUserRewards(HttpHeaders headers,
                                      @PathVariable String id,
                                      @PathVariable String battleId) {
        String countryCode = headers.getFirst("countryCode");
        return null;
    }

    @PostMapping(value = "/user/{id}/enterFraud", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserFraud enterFraud(HttpHeaders headers,
                                @PathVariable String id,
                                @RequestBody UserFraud userFraud) {
        String countryCode = headers.getFirst("countryCode");
        return null;
    }

    @PostMapping(value = "/user/{id}/rewards/{battleId}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRewards updateUserRewards(HttpHeaders headers,
                                         @PathVariable String id,
                                         @PathVariable String battleId,
                                         @RequestBody UserRewards userRewards) {
        String countryCode = headers.getFirst("countryCode");
        return null;
    }

    @GetMapping(value = "/games/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Game getGame(HttpHeaders headers,
                        @PathVariable String id) {
        String countryCode = headers.getFirst("countryCode");
        return null;
    }

    @GetMapping(value = "/games/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> getAllGames(HttpHeaders headers) {
        String countryCode = headers.getFirst("countryCode");
        return null;
    }

    @PostMapping(value = "/games/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Game createGame(HttpHeaders headers, @RequestBody Game game) {
        String countryCode = headers.getFirst("countryCode");
        return null;
    }

    @PostMapping(value = "/games/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Game updateGame(HttpHeaders headers, @RequestBody Game game) {
        String countryCode = headers.getFirst("countryCode");
        return null;
    }
}
