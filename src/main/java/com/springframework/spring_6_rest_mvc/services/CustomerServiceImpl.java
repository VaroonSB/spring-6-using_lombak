package com.springframework.spring_6_rest_mvc.services;

import com.springframework.spring_6_rest_mvc.model.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private HashMap<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        CustomerDTO customer1 = CustomerDTO.builder().name("Muddi").age(14).id(UUID.randomUUID()).build();
        CustomerDTO customer2 = CustomerDTO.builder().name("Vroom").age(24).id(UUID.randomUUID()).build();
        CustomerDTO customer3 = CustomerDTO.builder()
                                           .name("Shroom")
                                           .age(34)
                                           .id(UUID.randomUUID())
                                           .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }

    @Override
    public List<CustomerDTO> getCustomerList() {
        return customerMap.values().stream().toList();
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMap.get(id));
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
        CustomerDTO saved = CustomerDTO.builder()
                                       .id(UUID.randomUUID())
                                       .name(customer.getName())
                                       .age(customer.getAge())
                                       .build();
        customerMap.put(saved.getId(), saved);
        return saved;
    }
}
