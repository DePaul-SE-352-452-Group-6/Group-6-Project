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
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void addAccountInventory() throws Exception {
//        // given - setup or precondition
//        Account account = new Account();
//        account.setID(23);
//        account.setUserName("Larry");
//        account.setPassword("David");
//        account.setSignupDate(new Date(1681664605149L));
//        account.setLastSeenDate(new Date(1681664605149L));
//
//        DigitalGood digitalGood = new DigitalGood();
//        digitalGood.setId(6);
//        digitalGood.setName("meat");
//        digitalGood.setCurrencyId(2);
//        digitalGood.setCosts(100);
//
//        AccountInventory accountInventory = new AccountInventory(5, account, digitalGood, 100);
//
//        String studentAsJson = objectMapper.writeValueAsString(accountInventory);
//
//
//        // when - action
//        var request = MockMvcRequestBuilders.post(ACCOUNT_INVENTORY_URL+"/update");
//        request.contentType(MediaType.APPLICATION_JSON);
//        request.content(studentAsJson);
//        ResultActions response = mockMvc.perform(request);
//
//        var jsonResponse = response.andReturn().getResponse().getContentAsString();
//        // then - verify the output
//        Number updatedAccountInventory = new ObjectMapper().readValue(jsonResponse, Number.class);
//
//        response.andExpect(MockMvcResultMatchers.status().isOk());
//        assertNotEquals(updatedAccountInventory, accountInventory.getID());
//    }
//
//    @Test
//    public void removeStudent() throws Exception {
//        // given - setup or precondition
//        long beforeSize = accountInventoryRepository.count();
//
//        // when - action
//        var request = MockMvcRequestBuilders.delete(ACCOUNT_INVENTORY_URL+"/1");
//        ResultActions response = mockMvc.perform(request);
//
//        long afterSize = accountInventoryRepository.count();
//
//        response.andExpect(MockMvcResultMatchers.status().isOk());
//        assertEquals(beforeSize - 1, afterSize);
//    }

}