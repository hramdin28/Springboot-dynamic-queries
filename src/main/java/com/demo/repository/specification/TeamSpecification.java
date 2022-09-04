package com.demo.repository.specification;

import com.demo.model.Player;
import com.demo.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TeamSpecification {

    /**
     * Specification function to find teams by players name (find parent by filtering children)
     * Sub query is used
     *
     * @param playersNames child entity player names
     * @return Specification<Team>
     */
    public Specification<Team> findTeamsByPlayerName(List<String> playersNames) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> playerPredicates = new ArrayList<>();

            var playerSubQuery = query.subquery(Player.class);
            var playerSubRoot = playerSubQuery.from(Player.class);
            playerSubQuery.select(playerSubRoot);

            if (!playersNames.isEmpty()) {
                playerPredicates.add(playerSubRoot.get("name").in(playersNames));
            }

            var foreignKeyMatchPredicate = root.get("id").in(playerSubRoot.get("team").get("id"));

            playerPredicates.add(foreignKeyMatchPredicate);

            playerSubQuery.where(playerPredicates.toArray(new Predicate[0]));

            return criteriaBuilder.exists(playerSubQuery);
        };
    }
}
