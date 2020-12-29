package com.kkukielka.services.inventory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Disabled // for manual test
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    private final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");

    @BeforeEach
    void setUp() {

    }

    @Test
    public void getOnhandInventory() {
        Integer quantity = beerInventoryService.getOnhandInventory(BEER_1_UUID);

        Assertions.assertEquals(50, quantity);
    }

}