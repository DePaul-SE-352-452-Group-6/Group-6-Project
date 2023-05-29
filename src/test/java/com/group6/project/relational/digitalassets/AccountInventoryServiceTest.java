package com.group6.project.relational.digitalassets;

import com.group6.project.relational.account.Account;
import com.group6.project.relational.account.AccountRepository;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountInventoryServiceTest {

    private static final String ACCOUNT_INVENTORY_URL = "/api/accounts/inventories";

    @Autowired
    private AccountInventoryRepository accountInventoryRepository;

    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private DigitalGoodsRepository digitalRepo;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getAllAccountInventory() throws Exception {
        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(ACCOUNT_INVENTORY_URL));


        var recordCount = (int) accountInventoryRepository.count();

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(recordCount)));
    }



//    TODO these two methods failed, should debug
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addAccountInventory() throws Exception {
        // given - setup or precondition
        long beforeSize = accountInventoryRepository.count();
        Account account = Account.builder()
                            .ID(23)
                            .userName("Larry")
                            .password("David")
                            .signupDate(new Date(1681664605149L))
                            .lastSeenDate(new Date(1681664605149L))
                            .build();

        accountRepo.save(account);

        DigitalGood digitalGood = new DigitalGood();
        digitalGood.setId(6);
        digitalGood.setName("meat");
        digitalGood.setCurrencyId(2);
        digitalGood.setCosts(100);
        digitalRepo.save(digitalGood);

        AccountInventoryRequest accountInventoryRequest = new AccountInventoryRequest(0, account.getID(), digitalGood.getId(), 500);

        String digitalGoodAsJson = objectMapper.writeValueAsString(accountInventoryRequest);

        // when - action
        var request = MockMvcRequestBuilders.post(ACCOUNT_INVENTORY_URL+"/update");
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(digitalGoodAsJson);
        ResultActions response = mockMvc.perform(request);

        long afterSize = accountInventoryRepository.count();

        var jsonResponse = response.andReturn().getResponse().getContentAsString();
         //then - verify the output
        Long newId = new ObjectMapper().readValue(jsonResponse, Long.class);

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertNotEquals(newId , accountInventoryRequest.getId());
        assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    public void removeAccountInventory() throws Exception {
        // given - setup or precondition
        long beforeSize = accountInventoryRepository.count();

        // when - action
        var request = MockMvcRequestBuilders.delete(ACCOUNT_INVENTORY_URL+"/1");
        ResultActions response = mockMvc.perform(request);

        long afterSize = accountInventoryRepository.count();

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(beforeSize - 1, afterSize);
    }

}
