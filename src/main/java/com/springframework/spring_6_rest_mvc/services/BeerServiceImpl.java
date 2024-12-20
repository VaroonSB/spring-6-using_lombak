package com.springframework.spring_6_rest_mvc.services;

import com.springframework.spring_6_rest_mvc.model.BeerDTO;
import com.springframework.spring_6_rest_mvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl
        implements BeerService {

    private Map<UUID, BeerDTO> beerMap;

    public BeerServiceImpl() {

        this.beerMap = new HashMap<>();

        BeerDTO beer1 = BeerDTO.builder()
                               .id(UUID.randomUUID())
                               .version(1)
                               .beerName("GlenVilet")
                               .beerStyle(BeerStyle.PALE_ALE)
                               .upc("1234")
                               .price(BigDecimal.valueOf(12.99))
                               .quantityOnHand(122)
                               .createdDate(LocalDateTime.now())
                               .updatedDate(LocalDateTime.now())
                               .build();

        BeerDTO beer2 = BeerDTO.builder()
                               .id(UUID.randomUUID())
                               .version(2)
                               .beerName("GlenKing")
                               .beerStyle(BeerStyle.ALE)
                               .upc("5678")
                               .price(BigDecimal.valueOf(19.99))
                               .quantityOnHand(300)
                               .createdDate(LocalDateTime.now())
                               .updatedDate(LocalDateTime.now())
                               .build();

        BeerDTO beer3 = BeerDTO.builder()
                               .id(UUID.randomUUID())
                               .version(3)
                               .beerName("GlenAce")
                               .beerStyle(BeerStyle.PILSNER)
                               .upc("9012")
                               .price(BigDecimal.valueOf(20))
                               .quantityOnHand(4)
                               .createdDate(LocalDateTime.now())
                               .updatedDate(LocalDateTime.now())
                               .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public List<BeerDTO> getBeerList() {
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Optional<BeerDTO> getBeerById(@NonNull UUID id) {
        log.info("Get Beer Id in service is called");
        return Optional.ofNullable(beerMap.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(@NonNull BeerDTO beer) {
        BeerDTO savedBeer = BeerDTO.builder()
                                   .id(UUID.randomUUID())
                                   .beerName(beer.getBeerName())
                                   .beerStyle(beer.getBeerStyle())
                                   .upc(beer.getUpc())
                                   .price(beer.getPrice())
                                   .quantityOnHand(beer.getQuantityOnHand())
                                   .createdDate(LocalDateTime.now())
                                   .updatedDate(LocalDateTime.now())
                                   .build();
        beerMap.put(savedBeer.getId(), savedBeer);
        return savedBeer;
    }

    @Override
    public BeerDTO updateBeerById(UUID beerId, BeerDTO beer) {
        BeerDTO existing = beerMap.get(beerId);
        existing.setBeerName(beer.getBeerName());
        existing.setBeerStyle(beer.getBeerStyle());
        existing.setPrice(beer.getPrice());
        existing.setQuantityOnHand(beer.getQuantityOnHand());

        return beerMap.put(existing.getId(), existing);
    }

    @Override
    public void deleteById(UUID beerId) {
        beerMap.remove(beerId);
    }

    @Override
    public BeerDTO patchBeerById(UUID beerId, BeerDTO beer) {
        BeerDTO existing = beerMap.get(beerId);

        if (StringUtils.hasText(beer.getBeerName())) {
            existing.setBeerName(beer.getBeerName());
        }

        if (beer.getBeerStyle() != null) {
            existing.setBeerStyle(beer.getBeerStyle());
        }

        if (beer.getPrice() != null) {
            existing.setPrice(beer.getPrice());
        }

        if (beer.getQuantityOnHand() != null) {
            existing.setQuantityOnHand(beer.getQuantityOnHand());
        }

        if (StringUtils.hasText(beer.getUpc())) {
            existing.setUpc(beer.getUpc());
        }
        return existing;
    }
}
