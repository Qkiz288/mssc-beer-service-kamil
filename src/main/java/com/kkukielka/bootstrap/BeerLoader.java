package com.kkukielka.bootstrap;

import com.kkukielka.repositories.BeerRepository;
import com.kkukielka.web.domain.Beer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

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
                    .beerStyle("Wheat")
                    .quantityToBrew(200)
                    .minOnHand(20)
                    .upc(293873819103947L)
                    .price(new BigDecimal("5.99"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Kormoran")
                    .beerStyle("IPA")
                    .quantityToBrew(100)
                    .minOnHand(10)
                    .upc(293873819103948L)
                    .price(new BigDecimal("7.99"))
                    .build());

            System.out.println("Loaded beers: " + beerRepository.count());
        }
    }
}
