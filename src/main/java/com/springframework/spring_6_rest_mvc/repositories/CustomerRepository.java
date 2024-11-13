package com.springframework.spring_6_rest_mvc.repositories;

import com.springframework.spring_6_rest_mvc.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
