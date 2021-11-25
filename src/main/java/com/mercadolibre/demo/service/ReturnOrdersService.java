package com.mercadolibre.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.demo.dto.ReturnOrdersDTO;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.ReturnOrders;
import com.mercadolibre.demo.repository.ItemOfProductRepository;
import com.mercadolibre.demo.repository.ReturnOrdersRepository;

@Service
public class ReturnOrdersService {

	private ReturnOrdersRepository returnOrdersRepository; 
	private ItemOfProductRepository itemOfProductRepository;

	@Autowired
	public ReturnOrdersService(ReturnOrdersRepository returnOrdersRepository, ItemOfProductRepository itemOfProductRepository) {
		this.returnOrdersRepository = returnOrdersRepository;
		this.itemOfProductRepository = itemOfProductRepository;
	}


	public ReturnOrders save(ReturnOrdersDTO dto) throws Exception {
		if (dto.getDamage().equals("Sim") || dto.getDamage().equals("Não")) {
			ReturnOrders returnOrders = convertReturnOrdersToDTO(dto);
			returnOrders.setReturnCode(createReturnCode());
			return returnOrdersRepository.save(returnOrders);
		} else {
			throw new Exception("O dano(damege) deve ser Sim ou Não");
		}
	}

	public List<ReturnOrders> listReturnOrders() {
		return returnOrdersRepository.findAll();
	}

	public List<ReturnOrders> findReturnOrdersById(Long id) throws Exception {
		if(returnOrdersRepository.findById(id).isPresent()) {
			return returnOrdersRepository.listId(id);
		} else {
			throw new Exception("Id não cadastrado");
		}

	}

	public String createReturnCode() {
		String numbers = "1234567890";
		String sequenceInit = "";
		char finalSequence;
		for (int k = 0;k<7;k++){
			finalSequence=numbers.charAt((int) Math.floor(Math.random() * numbers.length()));
			sequenceInit += Character.toString(finalSequence);
		}
		return "ML"+sequenceInit+"BR";
	}
	
	public Optional<ReturnOrders> getIdReturnOrders(ReturnOrders order) {
		Optional<ReturnOrders> id = returnOrdersRepository.findById(order.getIdReturnOrders());
		return id;
	}

	public Optional<ItemOfProduct> getitemOfProduct(ReturnOrdersDTO dto) {
		Optional<ItemOfProduct> iTemOfProduct = itemOfProductRepository.findById(dto.getIdItemOfProduct());
		return iTemOfProduct;
	}

	public ReturnOrders convertReturnOrdersToDTO(ReturnOrdersDTO dto) throws Exception {
		if(getitemOfProduct(dto).isPresent()) {
			ReturnOrders returnOrders = new ReturnOrders();
			returnOrders.setIdItemOfProduct(dto.getIdItemOfProduct());
			returnOrders.setReason(dto.getReason());
			returnOrders.setDamage(dto.getDamage());
			return returnOrders;
		} else {
			throw new Exception("Id de ItemOfProduct não está cadastrado");
		}
	}
}
