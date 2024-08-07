package com.springframework.spring_6_rest_mvc.services;

import com.springframework.spring_6_rest_mvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    List<Beer> getBeerList();

    Beer getBeerById(UUID id);

    Beer saveNewBeer(Beer beer);

    Beer updateBeerById(UUID beerId, Beer beer);

    void deleteById(UUID beerId);

    Beer patchBeerById(UUID beerId, Beer beer);
}
