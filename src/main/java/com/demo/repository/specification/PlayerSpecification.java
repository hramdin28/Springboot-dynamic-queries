package com.demo.repository.specification;

import com.demo.model.Player;
import com.demo.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PlayerSpecification {

    /**
     * Find Players by Team name (find child by parent)
     *
     * @param teams Team names
     * @return Specification<Player>
     */
    public Specification<Player> findPlayersByTeam(List<String> teams) {
        return (root, query, criteriaBuilder) -> {

            Join<Player, Team> team = (Join) root.fetch("team", JoinType.LEFT);

            List<Predicate> predicates = new ArrayList<>();

            if (!teams.isEmpty()) {
                predicates.add(team.get("name").in(teams));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
