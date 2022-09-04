package com.demo.mapper;

import com.demo.dto.PlayerDto;
import com.demo.dto.PlayerWithTeamDto;
import com.demo.model.Player;
import org.mapstruct.Mapper;

@Mapper
public interface PlayerMapper {
    PlayerDto toPlayerDto(Player team);

    PlayerWithTeamDto toPlayerWithTeamDto(Player team);
}
