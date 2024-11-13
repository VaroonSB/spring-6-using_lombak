package com.springframework.spring_6_rest_mvc.controller;

import com.springframework.spring_6_rest_mvc.model.CustomerDTO;
import com.springframework.spring_6_rest_mvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping()
    public List<CustomerDTO> getCustomerList() {
        log.info("get customer list triggered");
        return customerService.getCustomerList();
    }

    @GetMapping(value = "{customerId}")
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID id) {
        log.info("get customer by id triggered");
        return customerService.getCustomerById(id);
    }

    @PostMapping()
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customer) {
        log.info("create customer triggered");
        return customerService.createCustomer(customer);
    }
}
