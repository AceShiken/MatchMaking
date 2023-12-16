package com.chitchat.matchmaking.service;

import com.chitchat.matchmaking.dto.requests.GameDto;
import com.chitchat.matchmaking.exceptions.InvalidRequestException;
import com.chitchat.matchmaking.mapper.DtoToEntityMapper;
import com.chitchat.matchmaking.models.Game;
import com.chitchat.matchmaking.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private DtoToEntityMapper dtoToEntityMapper;

    public Game getGame(int id) throws InvalidRequestException {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if(!optionalGame.isPresent()) {
            throw new InvalidRequestException("Invalid Game Id");
        }
        return optionalGame.get();
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game createGame(GameDto gameDto) {
        return gameRepository.insert(dtoToEntityMapper.convertToEntity(gameDto));
    }

    public Game updateGame(GameDto gameDto) {
        return gameRepository.save(dtoToEntityMapper.convertToEntity(gameDto));
    }
}
