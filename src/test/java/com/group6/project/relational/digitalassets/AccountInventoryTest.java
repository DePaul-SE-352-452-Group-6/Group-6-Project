package com.group6.project.relational.digitalassets;

import com.group6.project.relational.account.Account;
import com.group6.project.relational.account.AccountCurrency;
import com.group6.project.relational.account.AccountCurrencyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
@SpringBootTest
public class AccountInventoryTest {
    @Autowired
    private AccountInventoryRepository accountInventoryRepository;

    @DisplayName("Test Adding Account Inventory")
    @Test
    public void testAddingAccountInventoryComposition() {

        Account account = new Account();
        account.setID(23);
        account.setUserName("Larry");
        account.setPassword("David");
        account.setSignupDate(new Date(1681664605149L));
        account.setLastSeenDate(new Date(1681664605149L));

        AccountInventory accountInventory = new AccountInventory();
        accountInventory.setId(100);
        accountInventory.setAccount(account);
        accountInventory.setDigitalGoodID(1);
        accountInventory.setAmount(3);

        var beforeAdd = accountInventoryRepository.count();
        accountInventoryRepository.save(accountInventory);
        var afterAdd = accountInventoryRepository.count();

        assertEquals(beforeAdd + 1, afterAdd);

        var foundAccount = accountInventoryRepository.findByAccountID(2);
        assertEquals(2, foundAccount.size());
    }
}
