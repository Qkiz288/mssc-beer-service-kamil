package com.kkukielka.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkukielka.bootstrap.BeerLoader;
import com.kkukielka.services.BeerService;
import com.kkukielka.web.model.BeerDto;
import com.kkukielka.web.model.BeerPagedList;
import com.kkukielka.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void listBeersWithNullShowInventory() throws Exception {
        given(beerService.listBeers(anyString(), any(), any(), anyBoolean())).willReturn(getBeerPagedList());

        mockMvc.perform(get("/api/v1/beer").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void listBeersWithFalseShowInventory() throws Exception {
        given(beerService.listBeers(anyString(), any(), any(), anyBoolean())).willReturn(getBeerPagedList());

        mockMvc.perform(get("/api/v1/beer")
                .param("showInventoryOnHand", "false")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void listBeersWithTrueShowInventory() throws Exception {
        given(beerService.listBeers(anyString(), any(), any(), anyBoolean())).willReturn(getBeerPagedList());

        mockMvc.perform(get("/api/v1/beer")
                .param("showInventoryOnHand", "true")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getBeerById() throws Exception {
        given(beerService.getById(any(), anyBoolean())).willReturn(getValidBeerDto());

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto newBeer = getValidBeerDto();
        String newBeerJson = objectMapper.writeValueAsString(newBeer);

        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());

        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newBeerJson)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto updatedBeer = getValidBeerDto();
        String updatedBeerJson = objectMapper.writeValueAsString(updatedBeer);

        given(beerService.updateBeer(any(), any())).willReturn(getValidBeerDto());

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
            .contentType(MediaType.APPLICATION_JSON)
            .content(updatedBeerJson)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    }

    @Test
    void getBeerByUpc() throws Exception {
        given(beerService.getByUpc(anyString())).willReturn(getValidBeerDto());
        BeerDto expectedBeerDto = getValidBeerDto();

        MvcResult result = mockMvc.perform(get("/api/v1/beerUpc/" + BeerLoader.BEER_1_UPC)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        BeerDto responseBeer = objectMapper.readValue(responseBody, BeerDto.class);

        Assertions.assertEquals(responseBeer.getBeerName(), expectedBeerDto.getBeerName());
        Assertions.assertEquals(responseBeer.getBeerStyle(), expectedBeerDto.getBeerStyle());
        Assertions.assertEquals(responseBeer.getUpc(), expectedBeerDto.getUpc());
        Assertions.assertEquals(responseBeer.getPrice(), expectedBeerDto.getPrice());
    }

    private BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("Test Beer")
                .beerStyle(BeerStyleEnum.IPA)
                .upc(BeerLoader.BEER_1_UPC)
                .price(new BigDecimal("1.00"))
                .build();
    }

    private BeerPagedList getBeerPagedList() {
        return new BeerPagedList(List.of(getValidBeerDto()));
    }
}