package com.group6.project.relational.digitalassets;

import com.group6.project.relational.account.Account;
import com.group6.project.relational.account.AccountCurrency;
import com.group6.project.relational.account.AccountCurrencyRepository;
import com.group6.project.relational.account.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DataJpaTest
//@ActiveProfiles("test")
@SpringBootTest
public class AccountInventoryTest {

    /**
     * bug fixed
     */

    @Autowired
    private AccountInventoryRepository accountInventoryRepository;

    @DisplayName("Test Adding Account Inventory")
    @Test
    public void testAddingAccountInventoryComposition() {

        DigitalGood digitalGood = new DigitalGood();
        digitalGood.setId(3);
        digitalGood.setName("shoe");
        digitalGood.setCurrencyId(1);
        digitalGood.setCosts(100);

        AccountInventory accountInventory = new AccountInventory();
        accountInventory.setID(5);
        accountInventory.setDigitalGood(digitalGood);
        accountInventory.setAmount(1);

        var beforeAdd = accountInventoryRepository.count();
        accountInventoryRepository.save(accountInventory);
        var afterAdd = accountInventoryRepository.count();

        assertEquals(beforeAdd + 1, afterAdd);

        var foundAccount = accountInventoryRepository.findByAccountID(1);
        assertEquals(1, foundAccount.size());
    }
}
