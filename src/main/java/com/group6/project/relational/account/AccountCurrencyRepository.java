package com.group6.project.relational.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountCurrencyRepository extends JpaRepository<AccountCurrency, Long>{

    public List<AccountCurrency> findByAccountID(Integer id);

}
