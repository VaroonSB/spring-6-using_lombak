package com.springframework.spring_6_rest_mvc.controller;

import com.springframework.spring_6_rest_mvc.entities.Customer;
import com.springframework.spring_6_rest_mvc.model.CustomerDTO;
import com.springframework.spring_6_rest_mvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void getCustomerList() {
        List<CustomerDTO> customerDTOS = customerController.getCustomerList();

        assertThat(customerDTOS.size()).isGreaterThan(0);
    }

    @Transactional
    @Rollback
    @Test
    void getEmptyCustomerList() {
        customerRepository.deleteAll();

        List<CustomerDTO> customerDTOS = customerController.getCustomerList();

        assertThat(customerDTOS.size()).isEqualTo(0);
    }

    @Test
    void getCustomerById() {
        Customer customer = customerRepository.findAll()
                                              .get(0);

        CustomerDTO customerDTO = customerController.getCustomerById(customer.getId());

        assertThat(customerDTO).isNotNull();
    }

    @Test
    void testCustomerNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.getCustomerById(UUID.randomUUID());
        });
    }
}