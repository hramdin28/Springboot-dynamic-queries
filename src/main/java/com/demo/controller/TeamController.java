package com.demo.controller;

import com.demo.dto.TeamWithPlayersDto;
import com.demo.repository.TeamRepository;
import com.demo.repository.custom.CustomTeamQueryDslRepository;
import com.demo.repository.specification.TeamSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.demo.mapper.MapperList.TEAM_MAPPER;

@RequiredArgsConstructor
@RequestMapping(value = "/team")
@RestController
public class TeamController {
    private final TeamRepository teamRepository;
    private final CustomTeamQueryDslRepository customTeamQueryDslRepository;
    private final TeamSpecification teamSpecification;

    @GetMapping(value = "/byPlayer", produces = "application/json")
    public Set<TeamWithPlayersDto> findTeamsByPlayer(@RequestParam List<String> playerNames) {


        return teamRepository.findAll(teamSpecification.findTeamsByPlayerName(playerNames))
                .stream()
                .map(TEAM_MAPPER::toTeamWithPlayerDto)
                .collect(Collectors.toSet());
    }

    @GetMapping(value = "/byPlayerCustomQueryDsl", produces = "application/json")
    public List<TeamWithPlayersDto> findTeamsByPlayerCustomQueryDsl(@RequestParam String[] playerNames) {

        return customTeamQueryDslRepository.findTeamsByPlayerName(playerNames)
                .stream()
                .map(TEAM_MAPPER::toTeamWithPlayerDto)
                .toList();
    }

}
