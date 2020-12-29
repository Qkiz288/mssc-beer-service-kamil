package com.kkukielka.bootstrap;

import com.kkukielka.repositories.BeerRepository;
import com.kkukielka.web.domain.Beer;
import com.kkukielka.web.model.BeerStyleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loaderBeerObjects();
    }

    private void loaderBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Paulaner")
                    .beerStyle(BeerStyleEnum.WHEAT.name())
                    .quantityToBrew(200)
                    .minOnHand(20)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("5.99"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Kormoran")
                    .beerStyle(BeerStyleEnum.IPA.name())
                    .quantityToBrew(100)
                    .minOnHand(10)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("7.99"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Zatecky")
                    .beerStyle(BeerStyleEnum.PILSNER.name())
                    .quantityToBrew(100)
                    .minOnHand(10)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("7.99"))
                    .build());

            System.out.println("Loaded beers: " + beerRepository.count());
        }
    }
}
