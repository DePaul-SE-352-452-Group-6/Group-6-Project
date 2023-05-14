package com.group6.project.relational.digitalassets;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.group6.project.relational.digitalassets.Currency;
import com.group6.project.relational.digitalassets.CurrenciesRepository;

// Ensure there is no port conflict when running JUnit test
@SpringBootTest
@AutoConfigureMockMvc
public class CurrenciesServiceTest {
    private static final String CURRENCY_URL = "/api/currency";

    @Autowired
    private CurrenciesRepository currenciesRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getAllCurrencies() throws Exception {
        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(CURRENCY_URL));


        var recordCount = (int) currenciesRepository.count();

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(recordCount)));
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addCurrency() throws Exception {
        // given - setup or precondition
        Currency currency = new Currency();
        currency.setName("Silver");
        currency.setInitialCount(100);

        String CurrencyAsJson = objectMapper.writeValueAsString(currency);

        // when - action
        var request = MockMvcRequestBuilders.post(CURRENCY_URL);
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(CurrencyAsJson);
        ResultActions response = mockMvc.perform(request);

        var jsonResponse = response.andReturn().getResponse().getContentAsString();
        // then - verify the output
        Long updatedCurrency = new ObjectMapper().readValue(jsonResponse, Long.class);

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(updatedCurrency, currency.getId());
    }

    //Take care here, because each class's primary key has correlation with each other more or less
    //for example here, I have to implement CURRENCY_URL+"/4" to avoid conflict
    @Test
    public void removeCurrency() throws Exception {
        // given - setup or precondition
        long beforeSize = currenciesRepository.count();

        // when - action
        var request = MockMvcRequestBuilders.delete(CURRENCY_URL+"/4");
        ResultActions response = mockMvc.perform(request);

        long afterSize = currenciesRepository.count();

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(beforeSize-1, afterSize);
    }
}
