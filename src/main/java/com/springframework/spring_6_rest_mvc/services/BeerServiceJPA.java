package com.springframework.spring_6_rest_mvc.services;

import com.springframework.spring_6_rest_mvc.mappers.BeerMapper;
import com.springframework.spring_6_rest_mvc.model.BeerDTO;
import com.springframework.spring_6_rest_mvc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA
        implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public List<BeerDTO> getBeerList() {
        return beerRepository.findAll()
                             .stream()
                             .map(beerMapper::beerToBeerDto)
                             .collect(Collectors.toList());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return Optional.ofNullable(beerMapper.beerToBeerDto(beerRepository.findById(id)
                                                                          .orElse(null)));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        return null;
    }

    @Override
    public BeerDTO updateBeerById(UUID beerId, BeerDTO beer) {
        return null;
    }

    @Override
    public void deleteById(UUID beerId) {

    }

    @Override
    public BeerDTO patchBeerById(UUID beerId, BeerDTO beer) {
        return null;
    }
}
