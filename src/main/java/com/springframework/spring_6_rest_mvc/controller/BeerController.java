package com.springframework.spring_6_rest_mvc.controller;

import com.springframework.spring_6_rest_mvc.model.Beer;
import com.springframework.spring_6_rest_mvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class BeerController {
    private final BeerService beerService;

    @RequestMapping("api/v1/beers")
    public List<Beer> getBeerList() {
        return beerService.getBeerList();
    }

    public Beer getBeerById(UUID id) {
        log.info("Get Beer by Id in controller");
        return beerService.getBeerById(id);
    }
}
