package com.springframework.spring_6_rest_mvc.repositories;

import com.springframework.spring_6_rest_mvc.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
