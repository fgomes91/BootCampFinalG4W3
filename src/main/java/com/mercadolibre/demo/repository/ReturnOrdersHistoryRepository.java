package com.mercadolibre.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercadolibre.demo.model.ReturnOrdersHistory;

@Repository
public interface ReturnOrdersHistoryRepository extends JpaRepository<ReturnOrdersHistory, Long> {

	@Query(nativeQuery = true, value = "SELECT s.* from  return_orders_history s where s.idreturn_orders_history = ?1")
	List<ReturnOrdersHistory> listId (Long id);
}
