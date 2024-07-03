package com.springframework.spring_6_rest_mvc.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Customer {
    UUID id;
    String name;
    Integer age;
}
