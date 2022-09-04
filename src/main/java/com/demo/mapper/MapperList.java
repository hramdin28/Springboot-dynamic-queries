package com.demo.mapper;

import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;

@NoArgsConstructor
public final class MapperList {
    public static final TeamMapper TEAM_MAPPER = Mappers.getMapper(TeamMapper.class);
    public static final PlayerMapper PLAYER_MAPPER = Mappers.getMapper(PlayerMapper.class);

}
