package com.demo.repository;

import com.demo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamQueryDslRepository extends JpaRepository<Team, Long>, QuerydslPredicateExecutor<Team> {
}
