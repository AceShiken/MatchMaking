package com.chitchat.matchmaking.controller;

import com.chitchat.matchmaking.dto.requests.*;
import com.chitchat.matchmaking.exceptions.InvalidRequestException;
import com.chitchat.matchmaking.models.*;
import com.chitchat.matchmaking.service.BattleService;
import com.chitchat.matchmaking.service.GameService;
import com.chitchat.matchmaking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mm")
public class MatchMakingController {

    public static final String COUNTRY_CODE = "countrycode";

    public static final String OS = "os";

    public static final String IMEI = "imei";

    public static final String OS_VERSION = "osversion";

    public static final String RAM = "ram";
    @Autowired
    private BattleService battleService;

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/battles/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Battle getBattle(@RequestHeader HttpHeaders headers,
                            @PathVariable int id) throws InvalidRequestException {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return battleService.getBattle(id);
    }

    @GetMapping(value = "/battles/all/{gameId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Battle> getAllBattles(@RequestHeader HttpHeaders headers,
                                  @PathVariable int gameId) {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return battleService.getAllBattles(gameId);
    }

    @GetMapping(value = "/battles/{id}/participation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ParticipationInfo getBattleParticipationInfo(@RequestHeader HttpHeaders headers,
                                                        @PathVariable int id) throws InvalidRequestException {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return battleService.getBattleParticipationInfo(id);
    }

    @PostMapping(value = "/battles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Battle createBattle(@RequestHeader HttpHeaders headers, @RequestBody BattleDto battle) {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return battleService.createBattle(battle);
    }

    @PostMapping(value = "/battles/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Battle updateBattle(@RequestHeader HttpHeaders headers, @RequestBody BattleDto battle) {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return battleService.updateBattle(battle);
    }

    @GetMapping(value = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@RequestHeader HttpHeaders headers,
                        @PathVariable int id) throws InvalidRequestException {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return userService.getUser(id);
    }

    @PostMapping(value = "/user/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestHeader HttpHeaders headers,
                           @RequestBody UserDto userDto) {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        String imei = headers.getFirst(IMEI);
        Integer ramInGB = Integer.valueOf(headers.getFirst(RAM));
        String os = headers.getFirst(OS);
        String osVersion = headers.getFirst(OS_VERSION);
        userService.insertDeviceDetails(userDto.getId(), imei, ramInGB, os, osVersion);
        return userService.createUser(userDto);
    }

    @GetMapping(value = "/user/{id}/fraudInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserFraud getUserFraudInfo(@RequestHeader HttpHeaders headers,
                                      @PathVariable int id) throws InvalidRequestException {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return userService.getUserFraudInfo(id);
    }

    @GetMapping(value = "/user/{id}/rewards/{battleId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRewards getUserRewards(@RequestHeader HttpHeaders headers,
                                      @PathVariable int id,
                                      @PathVariable int battleId) throws InvalidRequestException {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return userService.getUserRewards(id, battleId);
    }

    @PostMapping(value = "/user/{id}/enterFraud", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserFraud enterFraud(@RequestHeader HttpHeaders headers,
                                @PathVariable int id,
                                @RequestBody FraudIntensityRequest fraudIntensityRequest) {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return userService.enterFraud(id, fraudIntensityRequest);
    }

    @PostMapping(value = "/user/{id}/rewards/{battleId}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRewards updateUserRewards(@RequestHeader HttpHeaders headers,
                                         @PathVariable int id,
                                         @PathVariable int battleId,
                                         @RequestBody UpdateUserRewardsRequest userRewards) throws InvalidRequestException {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return userService.updateUserRewards(id, battleId, userRewards);
    }

    @GetMapping(value = "/games/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Game getGame(@RequestHeader HttpHeaders headers,
                        @PathVariable int id) throws InvalidRequestException {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return gameService.getGame(id);
    }

    @GetMapping(value = "/games/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> getAllGames(@RequestHeader HttpHeaders headers) {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return gameService.getAllGames();
    }

    @PostMapping(value = "/games/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Game createGame(@RequestHeader HttpHeaders headers, @RequestBody GameDto game) {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return gameService.createGame(game);
    }

    @PostMapping(value = "/games/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Game updateGame(@RequestHeader HttpHeaders headers, @RequestBody GameDto game) {
        String countryCode = headers.getFirst(COUNTRY_CODE);
        return gameService.updateGame(game);
    }
}
