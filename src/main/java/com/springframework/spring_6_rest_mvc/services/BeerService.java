package com.springframework.spring_6_rest_mvc.services;

import com.springframework.spring_6_rest_mvc.model.BeerDTO;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    List<BeerDTO> getBeerList();

    BeerDTO getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    BeerDTO updateBeerById(UUID beerId, BeerDTO beer);

    void deleteById(UUID beerId);

    BeerDTO patchBeerById(UUID beerId, BeerDTO beer);
}
