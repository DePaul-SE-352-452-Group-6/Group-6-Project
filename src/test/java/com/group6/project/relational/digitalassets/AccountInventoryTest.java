package com.group6.project.relational.digitalassets;

import com.group6.project.relational.account.AccountCurrency;
import com.group6.project.relational.account.AccountCurrencyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class AccountInventoryTest {
    @Autowired
    private AccountInventoryRepository accountInventoryRepository;

    @DisplayName("Test Adding Account Inventory")
    @Test
    public void testAddingAccountInventoryComposition() {
        AccountInventory accountInventory = new AccountInventory();
        accountInventory.setId(100);
        accountInventory.setAccountID(1);
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
