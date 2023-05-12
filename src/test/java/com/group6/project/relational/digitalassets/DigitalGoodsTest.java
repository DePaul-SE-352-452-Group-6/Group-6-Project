package com.group6.project.relational.digitalassets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DataJpaTest
//@ActiveProfiles("test")
@SpringBootTest
public class DigitalGoodsTest {
    @Autowired
    private DigitalGoodsRepository digitalGoodsRepository;

    @DisplayName("Test Adding Digital Goods")
    @Test
    public void testAddingAccountComposition() {
        DigitalGood digitalGoods = new DigitalGood();
        digitalGoods.setId(100);
        digitalGoods.setName("level 2");
        digitalGoods.setCosts(500);
        digitalGoods.setCurrencyId(1);

        var beforeAdd = digitalGoodsRepository.count();
        digitalGoodsRepository.save(digitalGoods);
        var afterAdd = digitalGoodsRepository.count();

        assertEquals(beforeAdd + 1, afterAdd);

        var foundAccount = digitalGoodsRepository.findByName("weapon");
        assertEquals("weapon", foundAccount.getName());
    }
}
