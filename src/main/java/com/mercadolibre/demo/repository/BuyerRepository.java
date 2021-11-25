package com.mercadolibre.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.demo.model.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
	
}