package com.springframework.spring_6_rest_mvc.services;

import com.springframework.spring_6_rest_mvc.mappers.BeerMapper;
import com.springframework.spring_6_rest_mvc.mappers.CustomerMapper;
import com.springframework.spring_6_rest_mvc.model.CustomerDTO;
import com.springframework.spring_6_rest_mvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA
        implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMapper.customerToCustomerDto(
                customerRepository.findById(id)
                                  .orElse(null)));
    }

    @Override
    public List<CustomerDTO> getCustomerList() {
        return customerRepository.findAll()
                                 .stream()
                                 .map(customerMapper::customerToCustomerDto)
                                 .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
        return null;
    }
}
