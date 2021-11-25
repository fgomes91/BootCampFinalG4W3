package com.mercadolibre.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercadolibre.demo.model.ReturnOrders;

@Repository
public interface ReturnOrdersRepository extends JpaRepository<ReturnOrders, Long>{
	
    @Query(nativeQuery = true, value = "SELECT s.* from  return_orders s where s.idreturn_orders = ?1")
    List<ReturnOrders> listId (Long id);
}
