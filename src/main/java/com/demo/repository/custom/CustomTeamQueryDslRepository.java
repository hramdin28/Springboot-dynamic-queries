package com.demo.repository.custom;

import com.demo.model.QTeam;
import com.demo.model.Team;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CustomTeamQueryDslRepository {
    private final BaseQueryDsl baseQueryDsl;

    /**
     * Specification function to find teams by players name (find parent by filtering children)
     * Sub query is used
     *
     * @param playerNames child entity player names
     * @return List<Team>
     */
    public List<Team> findTeamsByPlayerName(String[] playerNames) {
        var team = QTeam.team;

        JPAQuery<Team> query = baseQueryDsl.query();

        return query.from(team)
                .where(team.players.any().name.in(playerNames))
                .fetch();
    }
}
