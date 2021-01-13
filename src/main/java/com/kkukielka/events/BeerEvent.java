package com.kkukielka.events;

import com.kkukielka.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Data
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 4813729952975080699L;
    private final BeerDto beerDto;
}
