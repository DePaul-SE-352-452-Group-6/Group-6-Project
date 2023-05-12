package com.group6.project.relational.digitalassets;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrenciesRepository extends JpaRepository<Currency, Long> {
    public Currency findByName(String name);

    //Find the information of currency by determining the initial allocation value
    public Currency findByInitialCount(Integer initialCount);
}
