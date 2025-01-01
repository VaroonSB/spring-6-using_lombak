package com.springframework.spring_6_rest_mvc.services;

import com.springframework.spring_6_rest_mvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Optional<CustomerDTO> getCustomerById(UUID id);

    List<CustomerDTO> getCustomerList();

    CustomerDTO createCustomer(CustomerDTO customer);

}
