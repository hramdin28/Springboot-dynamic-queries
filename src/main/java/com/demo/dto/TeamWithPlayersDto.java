package com.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TeamWithPlayersDto {
    private Long id;
    private String name;
    private Set<PlayerDto> players;
}
