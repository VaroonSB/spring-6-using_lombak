package com.springframework.spring_6_rest_mvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springframework.spring_6_rest_mvc.model.Beer;
import com.springframework.spring_6_rest_mvc.services.BeerService;
import com.springframework.spring_6_rest_mvc.services.BeerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    BeerServiceImpl beerServiceImpl;

    @BeforeEach
    void setUp() {
        beerServiceImpl = new BeerServiceImpl();
    }

    @Test
    void getBeerById() throws Exception {

        Beer testBeer = beerServiceImpl.getBeerList()
                                       .get(0);

        given(beerService.getBeerById(testBeer.getId())).willReturn(testBeer);

        mockMvc.perform(get("/api/v1/beer/" + testBeer.getId()).accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.id", is(testBeer.getId()
                                                      .toString())))
               .andExpect(jsonPath("$.beerName", is(testBeer.getBeerName())));
    }

    @Test
    void getBeerList() throws Exception {

        List<Beer> beerList = beerServiceImpl.getBeerList();

        given(beerService.getBeerList()).willReturn(beerList);

        ResultActions resultActions = mockMvc.perform(get("/api/v1/beer"));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void createNewBeer() throws Exception {
        Beer beer = beerServiceImpl.getBeerList()
                                   .get(0);
        beer.setVersion(null);
        beer.setId(null);

        given(beerService.saveNewBeer(any(Beer.class))).willReturn(beerServiceImpl.getBeerList()
                                                                                  .get(1));

        mockMvc.perform(post("/api/v1/beer").accept(MediaType.APPLICATION_JSON)
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(objectMapper.writeValueAsString(beer)))
               .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        Beer beer = beerServiceImpl.getBeerList()
                                   .get(0);

        mockMvc.perform(put("/api/v1/beer/" + beer.getId()).accept(MediaType.APPLICATION_JSON)
                                                          .contentType(MediaType.APPLICATION_JSON)
                                                          .content(objectMapper.writeValueAsString(
                                                                  beer)))
               .andExpect(status().isNoContent());

        verify(beerService).updateBeerById(any(UUID.class), any(Beer.class));
    }
}