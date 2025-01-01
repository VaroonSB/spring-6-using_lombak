package com.springframework.spring_6_rest_mvc.controller;

import com.springframework.spring_6_rest_mvc.model.BeerDTO;
import com.springframework.spring_6_rest_mvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {
    private final BeerService beerService;

    public static final String BEER_PATH = "/api/v1/beer/";
    public static final String BEER_PATH_ID = BEER_PATH + "{beerId}";

    @GetMapping(BEER_PATH)
    public List<BeerDTO> getBeerList() {
        log.info("Get BeerList in controller");
        return beerService.getBeerList();
    }

    @GetMapping(BEER_PATH_ID)
    public BeerDTO getBeerById(@PathVariable("beerId") UUID beerId) {
        log.info("Get Beer by Id in controller");
        return beerService.getBeerById(beerId)
                          .orElseThrow(NotFoundException::new);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity<HttpStatus> handlePost(@RequestBody BeerDTO beer) {
        BeerDTO savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId()
                                                           .toString());
        return new ResponseEntity<HttpStatus>(headers, HttpStatus.CREATED);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity<HttpStatus> updateById(@PathVariable("beerId") UUID beerId,
                                                 @RequestBody BeerDTO beer) {
        BeerDTO updatedBeer = beerService.updateBeerById(beerId, beer);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity<HttpStatus> updateBeerPatchById(@PathVariable("beerId") UUID beerId,
                                                          @RequestBody BeerDTO beer) {
        BeerDTO updatedBeer = beerService.patchBeerById(beerId, beer);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
