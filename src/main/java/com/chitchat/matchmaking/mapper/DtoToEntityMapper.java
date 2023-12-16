package com.chitchat.matchmaking.mapper;

import com.chitchat.matchmaking.dto.requests.BattleDto;
import com.chitchat.matchmaking.dto.requests.GameDto;
import com.chitchat.matchmaking.models.Battle;
import com.chitchat.matchmaking.models.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DtoToEntityMapper {
    Game convertToEntity(GameDto gameDto);
    Battle convertToEntity(BattleDto battleDto);
}
