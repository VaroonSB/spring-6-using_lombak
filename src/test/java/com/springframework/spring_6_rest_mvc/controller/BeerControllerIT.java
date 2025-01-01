package com.springframework.spring_6_rest_mvc.controller;

import com.springframework.spring_6_rest_mvc.entities.Beer;
import com.springframework.spring_6_rest_mvc.model.BeerDTO;
import com.springframework.spring_6_rest_mvc.repositories.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        Beer beer = beerRepository.findAll().get(0);

        BeerDTO beerDTO = beerController.getBeerById(beer.getId());

        assertThat(beerDTO).isNotNull();
    }

    @Test
    void testBeerIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            beerController.getBeerById(UUID.randomUUID());
        });
    }
}