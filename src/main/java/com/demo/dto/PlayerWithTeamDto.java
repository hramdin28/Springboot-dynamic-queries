package com.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerWithTeamDto {
    private Long id;

    private String name;

    private int num;

    private String position;

    private TeamDto team;
}
