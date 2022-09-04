package com.demo.controller;

import com.demo.dto.PlayerWithTeamDto;
import com.demo.model.QPlayer;
import com.demo.repository.PlayerQueryDslRepository;
import com.demo.repository.PlayerRepository;
import com.demo.repository.custom.CustomPlayerQueryDslRepository;
import com.demo.repository.specification.PlayerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

import static com.demo.mapper.MapperList.PLAYER_MAPPER;

@RequiredArgsConstructor
@RequestMapping(value = "/player")
@RestController
public class PlayerController {
    private final PlayerRepository playerRepository;
    private final PlayerQueryDslRepository playerQueryDslRepository;

    private final CustomPlayerQueryDslRepository customPlayerQueryDslRepository;
    private final PlayerSpecification playerSpecification;

    @GetMapping(value = "/byTeams", produces = "application/json")
    public List<PlayerWithTeamDto> findPlayersByTeam(@RequestParam List<String> teams) {
        return playerRepository.findAll(playerSpecification.findPlayersByTeam(teams))
                .stream()
                .map(PLAYER_MAPPER::toPlayerWithTeamDto)
                .toList();
    }

    @GetMapping(value = "/byTeamsQueryDsl", produces = "application/json")
    public List<PlayerWithTeamDto> findPlayersByTeamQueryDsl(@RequestParam String[] teams) {

        var player = QPlayer.player;

        var predicate = player.team.name.in(teams);

        var queryResult = playerQueryDslRepository.findAll(predicate);

        return StreamSupport.stream(queryResult.spliterator(), false)
                .map(PLAYER_MAPPER::toPlayerWithTeamDto)
                .toList();

    }

    @GetMapping(value = "/byTeamsQueryDslCustom", produces = "application/json")
    public List<PlayerWithTeamDto> findPlayersByTeamQueryDslCustom(@RequestParam String[] teams) {

        var queryResult = customPlayerQueryDslRepository.findByTeamNames(teams);

        return queryResult.stream()
                .map(PLAYER_MAPPER::toPlayerWithTeamDto)
                .toList();

    }
}
