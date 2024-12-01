package com.springframework.spring_6_rest_mvc.mappers;

import com.springframework.spring_6_rest_mvc.entities.Customer;
import com.springframework.spring_6_rest_mvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDto(Customer customer);
}
