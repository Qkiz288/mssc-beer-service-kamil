package com.kkukielka.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkukielka.web.model.BeerDto;
import com.kkukielka.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto newBeer = BeerDto.builder()
                .beerName("Test Beer")
                .beerStyle(BeerStyleEnum.IPA)
                .upc(123456789L)
                .price(new BigDecimal(5))
                .build();
        String newBeerJson = objectMapper.writeValueAsString(newBeer);

        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newBeerJson)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto updatedBeer = BeerDto.builder()
                .beerName("Test Beer")
                .beerStyle(BeerStyleEnum.IPA)
                .upc(123456789L)
                .price(new BigDecimal(5))
                .build();
        String updatedBeerJson = objectMapper.writeValueAsString(updatedBeer);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
            .contentType(MediaType.APPLICATION_JSON)
            .content(updatedBeerJson)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    }
}