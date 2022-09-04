package com.demo.mapper;

import com.demo.dto.TeamDto;
import com.demo.dto.TeamWithPlayersDto;
import com.demo.model.Team;
import org.mapstruct.Mapper;

@Mapper(uses = PlayerMapper.class)
public interface TeamMapper {
    TeamWithPlayersDto toTeamWithPlayerDto(Team team);

    TeamDto toTeamDto(Team team);
}
