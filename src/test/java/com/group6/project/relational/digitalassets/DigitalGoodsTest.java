package com.group6.project.relational.digitalassets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
        DigitalGoods digitalGoods = new DigitalGoods();
        digitalGoods.setId(100);
        digitalGoods.setName("weapon");
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
