package com.mercadolibre.demo.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mercadolibre.demo.dto.ReturnOrdersDTO;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.OrderStatus;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.model.PurchaseOrder;
import com.mercadolibre.demo.model.ReturnOrders;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.repository.ItemOfProductRepository;
import com.mercadolibre.demo.repository.ReturnOrdersRepository;

public class ReturnOrdersServiceTest {

	ReturnOrdersRepository mockReturnOrdersRepository = Mockito.mock(ReturnOrdersRepository.class); 
	ItemOfProductRepository mockItemOfProductRepository = Mockito.mock(ItemOfProductRepository.class);

	ReturnOrdersService returnOrdersService = new ReturnOrdersService(mockReturnOrdersRepository, mockItemOfProductRepository);


	ItemOfProduct createItemOfProduct() {
		Buyer buyer = new Buyer();
		buyer.setIdBuyer(1L);
		buyer.setName("Ayrton");
		buyer.setLastName("Senna");

		Product product = new Product();
		product.setId(1L);
		product.setName("Nhoque de batata com farofa entregue frio");
		product.setDescription("O melhor Nhoque com farofa da região");

		List<SalesAd> salesAdList = new ArrayList<>();
		SalesAd salesAd = new SalesAd();
		salesAd.setId(1L);
		salesAd.setMaximumTemperature(20F);
		salesAd.setMinimumTemperature(0F);
		salesAd.setPrice(45D);
		salesAd.setProduct(product);
		salesAdList.add(salesAd);

		List<ItemOfProduct> itemProductList = new ArrayList<>();

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setId(1L);
		purchaseOrder.setOrderStatus(OrderStatus.CARRINHO);
		purchaseOrder.setDate(LocalDate.now());;
		purchaseOrder.setIdBuyer(buyer);
		purchaseOrder.setItemOfProduct(itemProductList);

		ItemOfProduct itemOfProduct = new ItemOfProduct();
		itemOfProduct.setId(1L);
		itemOfProduct.setQuantity(3000L);
		itemOfProduct.setPurchaseOrder(purchaseOrder);
		itemOfProduct.setSalesAd(salesAd);
		itemProductList.add(itemOfProduct);
		return itemOfProduct;
	}

	ReturnOrdersDTO createReturnOrdersDTO() {
		ReturnOrdersDTO returnOrdersDTO = new ReturnOrdersDTO();
		returnOrdersDTO.setIdItemOfProduct(createItemOfProduct().getId());
		returnOrdersDTO.setReason("Produto entregue fora do prazo e com embalagem danificada");
		returnOrdersDTO.setDamage("Sim");
		return returnOrdersDTO;
	}

	ReturnOrdersDTO createFailReturnOrdersDTO() {
		ReturnOrdersDTO returnOrdersDTO = new ReturnOrdersDTO();
		returnOrdersDTO.setIdItemOfProduct(null);
		returnOrdersDTO.setReason("Produto entregue fora do prazo e com embalagem danificada");
		returnOrdersDTO.setDamage("Veio estragado");
		return returnOrdersDTO;
	}

	ReturnOrders createReturnOrders() {
		ReturnOrders returnOrders  = new ReturnOrders();
		returnOrders.setIdReturnOrders(1L);
		returnOrders.setIdItemOfProduct(createItemOfProduct().getId());
		returnOrders.setReturnCode(returnOrdersService.createReturnCode());
		returnOrders.setReason("Nhoque de batata com farofa entregue frio e com emabalagem danificada");
		returnOrders.setDamage("Sim");
		return returnOrders;
	}

	List<ReturnOrders> createListReturnOrders() {
		List<ReturnOrders> list = new ArrayList<>();
		ReturnOrders returnOrders  = new ReturnOrders();
		returnOrders.setIdReturnOrders(1L);
		returnOrders.setIdItemOfProduct(createItemOfProduct().getId());
		returnOrders.setReturnCode(returnOrdersService.createReturnCode());
		returnOrders.setReason("Nhoque de batata com farofa entregue frio e com emabalagem danificada");
		returnOrders.setDamage("Sim");
		list.add(returnOrders);
		return list;
	}
	
	List<ReturnOrders> createFailListReturnOrders() {
		List<ReturnOrders> list = new ArrayList<>();
		ReturnOrders returnOrders  = new ReturnOrders();
		returnOrders.setIdReturnOrders(null);
		returnOrders.setIdItemOfProduct(createItemOfProduct().getId());
		returnOrders.setReturnCode(returnOrdersService.createReturnCode());
		returnOrders.setReason("Nhoque de batata com farofa entregue frio e com emabalagem danificada");
		returnOrders.setDamage("Sim");
		list.add(returnOrders);
		return list;
	}

	@Test
	void testGetItemOfProduct() {

		createReturnOrdersDTO();

		Mockito.when(mockItemOfProductRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(createItemOfProduct()));
		returnOrdersService.getitemOfProduct(createReturnOrdersDTO());

		assertNotNull(createReturnOrdersDTO());	
	}

	@Test
	void testGetIdReturnOrders() {

		createReturnOrders();

		Mockito.when(mockItemOfProductRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(createItemOfProduct()));
		returnOrdersService.getIdReturnOrders(createReturnOrders());

		assertNotNull(createReturnOrders());
	}
	
	@Test
	void testFindReturnOrdersByIdNoSuccess() throws Exception {

		createFailListReturnOrders();
		
		Mockito.when(mockReturnOrdersRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(createListReturnOrders().get(0)));
		Mockito.when(mockReturnOrdersRepository.listId(Mockito.any(Long.class))).thenReturn(createListReturnOrders());
		

		Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
			returnOrdersService.findReturnOrdersById(createFailListReturnOrders().get(0).getIdReturnOrders());

		});

		assertTrue(exceptionThatWasThrown.getMessage(), true);
	}

	@Test
	void testFindReturnOrdersByIdWithSuccess() throws Exception {

		createListReturnOrders();
		
		Mockito.when(mockReturnOrdersRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(createListReturnOrders().get(0)));
		Mockito.when(mockReturnOrdersRepository.listId(Mockito.any(Long.class))).thenReturn(createListReturnOrders());
		returnOrdersService.findReturnOrdersById(createListReturnOrders().get(0).getIdReturnOrders());

		assertNotNull(createListReturnOrders().get(0).getIdReturnOrders());

		assertEquals(1L, createListReturnOrders().get(0).getIdReturnOrders());	
	}

	@Test
	void testListReturnOrders() {

		createListReturnOrders();

		Mockito.when(mockReturnOrdersRepository.findAll()).thenReturn(createListReturnOrders());
		returnOrdersService.listReturnOrders();

		assertNotNull(createListReturnOrders());
	}

	@Test
	void testConvertReturnOrdersToDTONoSuccess() {

		createFailReturnOrdersDTO();

		Mockito.when(mockItemOfProductRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(createItemOfProduct()));

		Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
			returnOrdersService.convertReturnOrdersToDTO(createFailReturnOrdersDTO());

		});

		assertTrue(exceptionThatWasThrown.getMessage(), true);
	}

	@Test
	void testConvertReturnOrdersToDTOWithSuccess() throws Exception {

		createReturnOrdersDTO();

		Mockito.when(mockItemOfProductRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(createItemOfProduct()));

		returnOrdersService.convertReturnOrdersToDTO(createReturnOrdersDTO());

		assertNotNull(createReturnOrdersDTO());
	}

	@Test
	void testSaveNoSuccess() throws Exception {

		ReturnOrdersDTO dto = createFailReturnOrdersDTO();
		ReturnOrders orders = createReturnOrders();


		Mockito.when(mockItemOfProductRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(createItemOfProduct()));
		Mockito.when(mockReturnOrdersRepository.save(Mockito.any(ReturnOrders.class))).thenReturn(orders);

		Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
			returnOrdersService.save(dto);

		});

		assertTrue(exceptionThatWasThrown.getMessage(), true);
	}

	@Test
	void testSaveWithSuccess() throws Exception {

		ReturnOrdersDTO dto = createReturnOrdersDTO();
		ReturnOrders orders = createReturnOrders();

		Mockito.when(mockItemOfProductRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(createItemOfProduct()));
		Mockito.when(mockReturnOrdersRepository.save(Mockito.any(ReturnOrders.class))).thenReturn(createReturnOrders());
		orders = returnOrdersService.convertReturnOrdersToDTO(dto);
		returnOrdersService.save(dto);

		assertNotNull(createReturnOrdersDTO());
	}
}
