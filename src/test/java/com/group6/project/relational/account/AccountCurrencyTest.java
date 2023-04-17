package com.group6.project.relational.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class AccountCurrencyTest {

    @Autowired
    private AccountCurrencyRepository accountCurrencyRepository;

    @DisplayName("Test Adding Account Currency")
    @Test
    public void testAddingAccountComposition() {
        AccountCurrency accountCurrency = new AccountCurrency();
        accountCurrency.setId(23);
        accountCurrency.setAccountID(1);
        accountCurrency.setCurrencyID(1);
        accountCurrency.setAmount(500);

        var b4Add = accountCurrencyRepository.count();
        accountCurrencyRepository.save(accountCurrency);
        var afterAdd = accountCurrencyRepository.count();

        assertEquals(b4Add + 1, afterAdd);

        var foundAccount = accountCurrencyRepository. findByAccountID(1);
        assertEquals(1, foundAccount.size());
    }
}
