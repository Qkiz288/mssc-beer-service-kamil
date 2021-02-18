package com.kkukielka.services.order;

import com.kkukielka.brewery.model.events.ValidateOrderRequest;
import com.kkukielka.brewery.model.events.ValidateOrderResult;
import com.kkukielka.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.kkukielka.config.JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final BeerOrderValidator beerOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest) {
        Boolean isValid = beerOrderValidator.validateOrder(validateOrderRequest.getBeerOrderDto());

        jmsTemplate.convertAndSend(VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult.builder()
                    .isValid(isValid)
                    .orderId(validateOrderRequest.getBeerOrderDto().getId())
                    .build());
    }
}
