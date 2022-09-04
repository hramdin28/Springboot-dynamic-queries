package com.demo.repository.custom;

import com.demo.model.Player;
import com.demo.model.QPlayer;
import com.demo.model.QTeam;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CustomPlayerQueryDslRepository {
    private final BaseQueryDsl baseQueryDsl;

    /**
     * Find playes by team names (find childs by parent)
     *
     * @param teamNames Team names
     * @return List<Player>
     */
    public List<Player> findByTeamNames(String[] teamNames) {
        var player = QPlayer.player;
        var team = QTeam.team;

        var predicate = team.name.in(teamNames);
        JPAQuery<Player> query = baseQueryDsl.query();

        return query.from(player)
                .leftJoin(player.team, team)
                .where(predicate)
                .fetchJoin()
                .fetch();
    }
}
