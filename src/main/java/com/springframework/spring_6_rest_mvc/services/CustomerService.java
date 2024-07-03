package com.springframework.spring_6_rest_mvc.services;

import com.springframework.spring_6_rest_mvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer getCustomerById(UUID id);

    List<Customer> getCustomerList();

    Customer createCustomer(Customer customer);

}
