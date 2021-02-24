package com.kkukielka.services;

import com.kkukielka.bootstrap.BeerLoader;
import com.kkukielka.mappers.BeerMapper;
import com.kkukielka.repositories.BeerRepository;
import com.kkukielka.web.domain.Beer;
import com.kkukielka.brewery.model.BeerDto;
import com.kkukielka.brewery.model.BeerStyleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class BeerServiceImplTest {

    private final String UPC = BeerLoader.BEER_1_UPC;

    @Mock
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    BeerService beerService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        beerService = new BeerServiceImpl(beerRepository, beerMapper);
    }

    @Test
    void getByUpc() {
        given(beerRepository.findBeerByUpc(anyString())).willReturn(Optional.of(getValidBeer()));
        Beer expectedBeer = getValidBeer();

        BeerDto beerDto = beerService.getByUpc(UPC);

        Assertions.assertEquals(beerDto.getBeerName(), expectedBeer.getBeerName());
        Assertions.assertEquals(beerDto.getBeerStyle().name(), expectedBeer.getBeerStyle());
        Assertions.assertEquals(beerDto.getUpc(), expectedBeer.getUpc());
        Assertions.assertEquals(beerDto.getPrice(), expectedBeer.getPrice());
    }

    public Beer getValidBeer() {
        return Beer.builder()
                .beerName("Test beer")
                .beerStyle(BeerStyleEnum.WHEAT.name())
                .upc(UPC)
                .price(new BigDecimal("1.23"))
                .build();
    }
}