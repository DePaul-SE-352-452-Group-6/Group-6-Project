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
import lombok.extern.log4j.Log4j2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@DataJpaTest
//@ActiveProfiles("test")
// @SpringBootTest
@Log4j2
public class AccountInventoryTest {

    /**
     * bug fixed
     */

    @Autowired
    private AccountInventoryRepository accountInventoryRepository;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private DigitalGoodsRepository digitalGoodRepo;

    @DisplayName("Test Adding Account Inventory")
    @Test
    public void testAddingAccountInventoryComposition() {

//        DigitalGood digitalGood = new DigitalGood();
//        digitalGood.setId(3);
//        digitalGood.setName("shoe");
//        digitalGood.setCurrencyId(1);
//        digitalGood.setCosts(100);

        AccountInventory accountInventory = new AccountInventory();
        accountInventory.setID(5);
        accountInventory.setDigitalGood(digitalGoodRepo.findById(1));
        accountInventory.setAccount(accountRepo.findById(1));
        accountInventory.setAmount(1);

        var beforeAdd = accountInventoryRepository.count();
        log.warn("before add: " + beforeAdd);
        accountInventoryRepository.save(accountInventory);
        var afterAdd = accountInventoryRepository.count();

        log.warn("after add: " + afterAdd);
        assertEquals(beforeAdd+1, afterAdd);

        var foundAccount = accountInventoryRepository.findByAccountID(1);
        log.warn("size " + foundAccount.size());
        assertEquals(2, foundAccount.size());

    }
}
