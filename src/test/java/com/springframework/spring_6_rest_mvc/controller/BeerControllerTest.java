package com.springframework.spring_6_rest_mvc.controller;

import com.springframework.spring_6_rest_mvc.model.Beer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BeerControllerTest {

    @Autowired
    BeerController beerController;

    @Test
    void getBeerById() {
        List<Beer> beers = beerController.getBeerList();
        log.info(beers.toString());
    }
}