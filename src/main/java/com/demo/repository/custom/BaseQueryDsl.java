package com.demo.repository.custom;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Component
public class BaseQueryDsl {
    private final EntityManager entityManager;

    public <T> JPAQuery<T> query() {
        return new JPAQuery<>(entityManager);
    }
}
