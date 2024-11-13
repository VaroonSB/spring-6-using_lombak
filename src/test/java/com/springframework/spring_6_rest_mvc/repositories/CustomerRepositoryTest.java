package com.springframework.spring_6_rest_mvc.repositories;

import com.springframework.spring_6_rest_mvc.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired CustomerRepository customerRepository;

    @Test
    void testSaveCustomer() {
        Customer customer = customerRepository.save(Customer.builder().name("MyName")
                                                            .build());

        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isNotNull();
    }
}