package com.group6.project.relational.digitalassets;

import com.group6.project.relational.account.AccountCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrenciesRepository extends JpaRepository<Currencies, Long> {
    public Currencies findByName(String name);
    public Currencies findById(Integer id);
}
