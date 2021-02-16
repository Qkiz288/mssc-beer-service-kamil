package com.kkukielka.brewery.model.events;

import com.kkukielka.brewery.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 4813729952975080699L;
    private BeerDto beerDto;
}
