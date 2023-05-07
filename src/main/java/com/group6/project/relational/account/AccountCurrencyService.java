package com.group6.project.relational.account;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/accounts/currencies")
@Tag(name = "Account Currency", description = "All of the currencies owned by all of the accounts")
@Log4j2
public class AccountCurrencyService {
    @Autowired
    private AccountCurrencyRepository repo;

    @GetMapping
    @Operation(summary = "Returns all the account currencies in the database")
    public List<AccountCurrency> list() {
        log.traceEntry("Enter list");
        var retval = repo.findAll();
        log.traceExit("Exit list", retval);
        return repo.findAll();
    }

    @PostMapping
    @Operation(summary = "Save the currency for an account and returns the account currency id")
    public void save(@RequestBody AccountCurrency AccountCurrency) {
        log.traceEntry("enter save", AccountCurrency);
        repo.save(AccountCurrency);
        log.traceExit("exit save", AccountCurrency);
    }


    @DeleteMapping
    @Operation(summary = "Delete the account currency")
    public void delete(long code) {
        log.traceEntry("Enter delete", code);
        repo.deleteById(code);
        log.traceExit("Exit delete");
    }
}