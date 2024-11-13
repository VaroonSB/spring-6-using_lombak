package com.springframework.spring_6_rest_mvc.repositories;

import com.springframework.spring_6_rest_mvc.entities.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer() {
        Beer beer = beerRepository.save(Beer.builder().beerName("My Bear")
                                            .build());

        assertThat(beer).isNotNull();
        assertThat(beer.getId()).isNotNull();
    }
}