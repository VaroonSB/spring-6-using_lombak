package com.springframework.spring_6_rest_mvc.services;

import com.springframework.spring_6_rest_mvc.model.Beer;
import com.springframework.spring_6_rest_mvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, Beer> beerMap;

    public BeerServiceImpl() {

        this.beerMap = new HashMap<>();

        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("GlenVilet")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("1234")
                .price(BigDecimal.valueOf(12.99))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now()).build();

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(2)
                .beerName("GlenKing")
                .beerStyle(BeerStyle.ALE)
                .upc("5678")
                .price(BigDecimal.valueOf(19.99))
                .quantityOnHand(300)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now()).build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(3)
                .beerName("GlenAce")
                .beerStyle(BeerStyle.PILSNER)
                .upc("9012")
                .price(BigDecimal.valueOf(20))
                .quantityOnHand(4)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now()).build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public List<Beer> getBeerList() {
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Beer getBeerById(UUID id) {

        log.info("Get Beer Id in service is called");

        return beerMap.get(id);
    }
}
