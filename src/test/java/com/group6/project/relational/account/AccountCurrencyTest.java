package com.group6.project.relational.account;

import com.group6.project.relational.digitalassets.AccountInventoryRepository;
import com.group6.project.relational.digitalassets.CurrenciesRepository;
import com.group6.project.relational.digitalassets.Currency;
import com.group6.project.relational.digitalassets.DigitalGoodsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DataJpaTest
//@ActiveProfiles("test")
@SpringBootTest
public class AccountCurrencyTest {

    @Autowired
    private AccountCurrencyRepository accountCurrencyRepository;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private CurrenciesRepository currencyRepo;

    @DisplayName("Test Adding Account Currency")
    @Test
    public void testAddingAccountComposition() {

//        Currency currencies = new Currency();
//        currencies.setId(1);
//        currencies.setInitialCount(500);
//        currencies.setName("Gold");

        AccountCurrency accountCurrency = new AccountCurrency();
        accountCurrency.setID(23);
        accountCurrency.setAccount(accountRepo.findById(1));
        accountCurrency.setCurrencyID(currencyRepo.findById(1));
        accountCurrency.setAmount(500);

        var b4Add = accountCurrencyRepository.count();
        accountCurrencyRepository.save(accountCurrency);
        var afterAdd = accountCurrencyRepository.count();

        assertEquals(b4Add + 1, afterAdd);

        var foundAccount = accountCurrencyRepository. findByAccountID(1L);
        assertEquals(2, foundAccount.size());
    }
}
