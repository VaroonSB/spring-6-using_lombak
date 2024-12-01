package com.springframework.spring_6_rest_mvc.services;

import com.springframework.spring_6_rest_mvc.mappers.CustomerMapper;
import com.springframework.spring_6_rest_mvc.model.CustomerDTO;
import com.springframework.spring_6_rest_mvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA
        implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDTO getCustomerById(UUID id) {
        return null;
    }

    @Override
    public List<CustomerDTO> getCustomerList() {
        return List.of();
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
        return null;
    }
}
