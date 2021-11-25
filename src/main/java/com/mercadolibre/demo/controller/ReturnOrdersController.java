package com.mercadolibre.demo.controller;

import java.util.List;
import java.util.Optional;

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

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.ReturnOrdersDTO;
import com.mercadolibre.demo.model.ReturnOrders;
import com.mercadolibre.demo.service.ReturnOrdersService;

@RestController
@RequestMapping("/api/v1/fresh-products/returnorders")
public class ReturnOrdersController implements SecurityController{
	
	@Autowired
	private ReturnOrdersService returnOrdersService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<ReturnOrders> saveReturnOrders(@Valid @RequestBody ReturnOrdersDTO dto) {
		try {
			ReturnOrders returnOrders = returnOrdersService.save(dto);
			return new ResponseEntity<>(returnOrders, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<ReturnOrders>> listAllReturnOrders(){
		List<ReturnOrders> returnOrders = returnOrdersService.listReturnOrders();
		return new ResponseEntity<>(returnOrders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<ReturnOrders> findReturnOrders(@PathVariable Long id){
		try {
			List<ReturnOrders> returnOrders = returnOrdersService.findReturnOrdersById(id);
			return new ResponseEntity(returnOrders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
	
}
