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

import com.group6.project.relational.digitalassets.DigitalGood;
import com.group6.project.relational.digitalassets.DigitalGoodsRepository;

// Ensure there is no port conflict when running JUnit test
@SpringBootTest
@AutoConfigureMockMvc
public class DigitalGoodsServiceTest {
    private static final String DIGITAL_GOODS_URL = "/api/digitalGoods";

    @Autowired
    private DigitalGoodsRepository digitalGoodsRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getAllDigitalGoods() throws Exception {
        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(DIGITAL_GOODS_URL));


        var recordCount = (int) digitalGoodsRepository.count();

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(recordCount)));
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addDigitalGood() throws Exception {
        // given - setup or precondition
        DigitalGood digitalGood = new DigitalGood();
        digitalGood.setName("key_item_2");
        digitalGood.setCurrencyId(3);
        digitalGood.setCosts(1500);

        String DigitalGoodAsJson = objectMapper.writeValueAsString(digitalGood);

        // when - action
        var request = MockMvcRequestBuilders.post(DIGITAL_GOODS_URL);
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(DigitalGoodAsJson);
        ResultActions response = mockMvc.perform(request);

        var jsonResponse = response.andReturn().getResponse().getContentAsString();
        // then - verify the output
        Number updatedCurrency = new ObjectMapper().readValue(jsonResponse, Number.class);

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertNotEquals(updatedCurrency, digitalGood.getId());
    }

    //Take care here, because each class's primary key has correlation with each other more or less
    //for example here, I have to implement DIGITAL_GOODS_URL+"/5" to avoid conflict
    @Test
    public void removeCurrency() throws Exception {
        // given - setup or precondition
        long beforeSize = digitalGoodsRepository.count();

        // when - action
        var request = MockMvcRequestBuilders.delete(DIGITAL_GOODS_URL+"/5");
        ResultActions response = mockMvc.perform(request);

        long afterSize = digitalGoodsRepository.count();

        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(beforeSize-1, afterSize);
    }
}
