package com.springframework.spring_6_rest_mvc.controller;

import com.springframework.spring_6_rest_mvc.model.CustomerDTO;
import com.springframework.spring_6_rest_mvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
    }
}