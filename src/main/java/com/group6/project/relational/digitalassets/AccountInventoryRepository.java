package com.group6.project.relational.digitalassets;

import com.group6.project.relational.account.AccountCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountInventoryRepository extends JpaRepository<AccountInventory, Long> {
    public List<AccountInventory> findByAccountID(Integer id);
}
