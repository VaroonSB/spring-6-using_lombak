package com.springframework.spring_6_rest_mvc.controller;

import com.springframework.spring_6_rest_mvc.entities.Beer;
import com.springframework.spring_6_rest_mvc.model.BeerDTO;
import com.springframework.spring_6_rest_mvc.repositories.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerControllerIT {

    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Test
    void getBeerList() {
        List<BeerDTO> beerDTOS = beerController.getBeerList();

        assertThat(beerDTOS.size()).isGreaterThan(0);
    }

    @Transactional
    @Rollback
    @Test
    void getEmptyBeerList() {
        beerRepository.deleteAll();

        List<BeerDTO> beerDTOS = beerController.getBeerList();

        assertThat(beerDTOS.size()).isEqualTo(0);
    }

    @Test
    void getBeerById() {
        Beer beer = beerRepository.findAll()
                                  .get(0);

        BeerDTO beerDTO = beerController.getBeerById(beer.getId());

        assertThat(beerDTO).isNotNull();
    }

    @Test
    void testBeerIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            beerController.getBeerById(UUID.randomUUID());
        });
    }

    @Transactional
    @Rollback
    @Test
    void saveNewBeer() {
        BeerDTO beerDTO = BeerDTO.builder()
                                 .beerName("test beer")
                                 .build();

        ResponseEntity<HttpStatus> responseEntity = beerController.handlePost(beerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders()
                                 .getLocation()).isNotNull();
        UUID uuid = UUID.fromString(responseEntity.getHeaders()
                                                    .getLocation()
                                                    .getPath()
                                                    .split("/")[4]);

        Optional<Beer> beer = beerRepository.findById(uuid);
        assertThat(beer).isNotNull();
    }
}