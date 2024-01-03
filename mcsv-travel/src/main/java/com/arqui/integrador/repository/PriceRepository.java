package com.arqui.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arqui.integrador.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    
}
