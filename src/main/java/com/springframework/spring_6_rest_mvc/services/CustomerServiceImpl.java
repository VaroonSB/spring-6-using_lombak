package com.springframework.spring_6_rest_mvc.services;

import com.springframework.spring_6_rest_mvc.model.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private HashMap<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        Customer customer1 = Customer.builder().name("Muddi").age(14).id(UUID.randomUUID()).build();
        Customer customer2 = Customer.builder().name("Vroom").age(24).id(UUID.randomUUID()).build();
        Customer customer3 = Customer.builder()
                .name("Shroom")
                .age(34)
                .id(UUID.randomUUID())
                .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }

    @Override
    public List<Customer> getCustomerList() {
        return customerMap.values().stream().toList();
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerMap.get(id);
    }

}
