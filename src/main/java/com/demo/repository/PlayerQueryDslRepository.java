package com.demo.repository;

import com.demo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerQueryDslRepository extends JpaRepository<Player, Long>, QuerydslPredicateExecutor<Player> {
}
