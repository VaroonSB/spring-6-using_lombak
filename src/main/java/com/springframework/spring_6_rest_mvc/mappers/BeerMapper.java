package com.springframework.spring_6_rest_mvc.mappers;

import com.springframework.spring_6_rest_mvc.entities.Beer;
import com.springframework.spring_6_rest_mvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO beerDTO);

    BeerDTO beerToBeerDto(Beer beer);
}
