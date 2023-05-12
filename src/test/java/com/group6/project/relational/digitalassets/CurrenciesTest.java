package com.group6.project.relational.digitalassets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DataJpaTest
//@ActiveProfiles("test")
@SpringBootTest
public class CurrenciesTest {
    @Autowired
    private CurrenciesRepository currenciesRepository;

    @DisplayName("Test Adding Currencies")
    @Test
    public void testAddingAccountComposition() {
        Currency currency = new Currency();
        //currency.setId(100);
        currency.setName("Sapphires");
        currency.setInitialCount(500);

        var beforeAdd = currenciesRepository.count();
        currenciesRepository.save(currency);
        var afterAdd = currenciesRepository.count();

        assertEquals(beforeAdd + 1, afterAdd);

        var foundAccount = currenciesRepository.findByName("Gems");
        assertEquals("Gems", foundAccount.getName());

    }
}
