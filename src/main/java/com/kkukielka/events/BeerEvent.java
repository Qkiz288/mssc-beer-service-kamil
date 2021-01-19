package com.kkukielka.events;

import com.kkukielka.web.model.BeerDto;
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
