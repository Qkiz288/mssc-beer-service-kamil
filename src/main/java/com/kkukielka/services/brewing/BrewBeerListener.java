package com.kkukielka.services.brewing;

import com.kkukielka.config.JmsConfig;
import com.kkukielka.common.events.BrewBeerEvent;
import com.kkukielka.common.events.NewInventoryEvent;
import com.kkukielka.repositories.BeerRepository;
import com.kkukielka.web.domain.Beer;
import com.kkukielka.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
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
