package com.springframework.spring_6_rest_mvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springframework.spring_6_rest_mvc.model.Customer;
import com.springframework.spring_6_rest_mvc.services.CustomerService;
import com.springframework.spring_6_rest_mvc.services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    void setUp() {
        customerServiceImpl = new CustomerServiceImpl();
    }

    @Test
    void getCustomerList() throws Exception {
        given(customerService.getCustomerList()).willReturn(customerServiceImpl.getCustomerList());

        ResultActions resultActions = mockMvc.perform(get("/api/v1/customer"));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void getCustomerById() throws Exception {
        Customer customer = customerServiceImpl.getCustomerList()
                                               .get(0);

        given(customerService.getCustomerById(customer.getId())).willReturn(customer);

        ResultActions resultActions = mockMvc.perform(get("/api/v1/customer/" + customer.getId()));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(jsonPath("$.id", is(customer.getId()
                                                            .toString())));
        resultActions.andExpect(jsonPath("$.name", is(customer.getName())));
    }

    @Test
    void createNewCustomer() throws Exception {
        Customer customer = customerServiceImpl.getCustomerList()
                                               .get(0);
        customer.setId(null);

        given(customerService.createCustomer(any(Customer.class))).willReturn(
                customerServiceImpl.getCustomerList()
                                   .get(1));

        mockMvc.perform(post("/api/v1/customer").accept(MediaType.APPLICATION_JSON)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(customer)))
               .andExpect(status().isOk());
    }

    @Test
    void getBeerByIdNotFound() throws Exception {

        given(customerService.getCustomerById(any(UUID.class))).willThrow(NotFoundException.class);

        mockMvc.perform(get("/api/v1/customer" + UUID.randomUUID()))
               .andExpect(status().isNotFound());
    }
}