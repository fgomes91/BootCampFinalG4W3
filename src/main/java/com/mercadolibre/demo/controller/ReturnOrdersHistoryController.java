package com.mercadolibre.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.demo.dto.ReturnOrdersHistoryDTO;
import com.mercadolibre.demo.model.ReturnOrdersHistory;
import com.mercadolibre.demo.service.ReturnOrdersHistoryService;

@RestController
@RequestMapping("/api/v1/fresh-products/returnOrdersHistory")
public class ReturnOrdersHistoryController {

	@Autowired
	private ReturnOrdersHistoryService returnOrdersHistoryService;

	@PostMapping(value = "/toDiscard")
	public ResponseEntity<ReturnOrdersHistory> toDiscard(@Valid @RequestBody ReturnOrdersHistoryDTO dto) {
		try {
			ReturnOrdersHistory order = returnOrdersHistoryService.toDiscard(dto);
			return new ResponseEntity<>(order, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	} 

	@PostMapping(value = "/increment")
	public ResponseEntity<ReturnOrdersHistory> increment(@Valid @RequestBody ReturnOrdersHistoryDTO dto) {
		try {
			ReturnOrdersHistory order = returnOrdersHistoryService.incrementInBatchStock(dto);
			return new ResponseEntity<>(order, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	} 

	@GetMapping(value = "/list")
	public ResponseEntity<List<ReturnOrdersHistory>> listReturnOrdersHistory(){
		List<ReturnOrdersHistory> returnOrders = returnOrdersHistoryService.listReturnOrdersHistory();
		return new ResponseEntity<>(returnOrders, HttpStatus.OK);
	}

	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<List<ReturnOrdersHistory>> findReturnOrdersHistoryById(@PathVariable Long id) throws Exception{
		try {
			List<ReturnOrdersHistory> returnOrders = returnOrdersHistoryService.findReturnOrdersHistoryById(id);
			return new ResponseEntity<>(returnOrders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
