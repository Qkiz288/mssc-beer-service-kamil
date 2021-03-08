package com.kkukielka.msscbeerservice.services.brewing;

import com.kkukielka.msscbeerservice.config.JmsConfig;
import com.kkukielka.brewery.model.events.BrewBeerEvent;
import com.kkukielka.brewery.model.events.NewInventoryEvent;
import com.kkukielka.msscbeerservice.repositories.BeerRepository;
import com.kkukielka.msscbeerservice.domain.Beer;
import com.kkukielka.brewery.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    @Transactional
    public void listen(BrewBeerEvent brewBeerEvent) {
        BeerDto beerDto = brewBeerEvent.getBeerDto();

        Beer beer = beerRepository.getOne(beerDto.getId());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug(String.format("Brewed beer %s, Quantity on hand: %s",
                beer.getMinOnHand(), beerDto.getQuantityOnHand()));

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }

}
