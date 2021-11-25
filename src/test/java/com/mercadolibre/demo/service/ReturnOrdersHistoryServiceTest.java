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
import com.mercadolibre.demo.dto.ReturnOrdersHistoryDTO;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.OrderStatus;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.model.PurchaseOrder;
import com.mercadolibre.demo.model.ReturnOrders;
import com.mercadolibre.demo.model.ReturnOrdersHistory;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.repository.BatchStockRepository;
import com.mercadolibre.demo.repository.ItemOfProductRepository;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import com.mercadolibre.demo.repository.ReturnOrdersHistoryRepository;
import com.mercadolibre.demo.repository.ReturnOrdersRepository;

public class ReturnOrdersHistoryServiceTest {

	ReturnOrdersHistoryRepository mockReturnOrdersHistoryRepository = Mockito.mock(ReturnOrdersHistoryRepository.class); 
	ReturnOrdersRepository mockReturnOrdersRepository = Mockito.mock(ReturnOrdersRepository.class); 
	BatchStockRepository mockBatchStockRepository = Mockito.mock(BatchStockRepository.class); 
	PurchaseOrderRepository mockPurchaseOrderRepository = Mockito.mock(PurchaseOrderRepository.class); 
	ItemOfProductRepository mockItemOfProductRepository = Mockito.mock(ItemOfProductRepository.class); 

	ReturnOrdersHistoryService returnOrdersHistoryService = new ReturnOrdersHistoryService(mockReturnOrdersHistoryRepository,
			mockReturnOrdersRepository, mockBatchStockRepository, mockPurchaseOrderRepository, mockItemOfProductRepository);

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

	PurchaseOrder createPurchaseOrder() {
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

		return purchaseOrder;
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
		returnOrders.setReturnCode("ML4365709BR");
		returnOrders.setReason("Nhoque de batata com farofa entregue frio e com emabalagem danificada");
		returnOrders.setDamage("Sim");
		return returnOrders;
	}

	List<ReturnOrders> createListReturnOrders() {
		List<ReturnOrders> list = new ArrayList<>();
		ReturnOrders returnOrders  = new ReturnOrders();
		returnOrders.setIdReturnOrders(1L);
		returnOrders.setIdItemOfProduct(createItemOfProduct().getId());
		returnOrders.setReturnCode("ML4365709BR");
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
		returnOrders.setReturnCode("ML4365709BR");
		returnOrders.setReason("Nhoque de batata com farofa entregue frio e com emabalagem danificada");
		returnOrders.setDamage("Sim");
		list.add(returnOrders);
		return list;
	}

	ReturnOrdersHistory createReturnOrdersHistoryDiscartYes() {
		ReturnOrdersHistory returnOrdersHistory = new ReturnOrdersHistory();
		returnOrdersHistory.setIdReturnOrdersHistory(1L);
		returnOrdersHistory.setIdPurchaseOrder(createPurchaseOrder().getId());
		returnOrdersHistory.setReturnOrders(createReturnOrders());
		returnOrdersHistory.setDiscard("Sim");
		return returnOrdersHistory;
	}

	ReturnOrdersHistory createFailReturnOrdersHistoryDiscart() {
		ReturnOrdersHistory returnOrdersHistory = new ReturnOrdersHistory();
		returnOrdersHistory.setIdReturnOrdersHistory(null);
		returnOrdersHistory.setIdPurchaseOrder(createPurchaseOrder().getId());
		returnOrdersHistory.setReturnOrders(createReturnOrders());
		returnOrdersHistory.setDiscard("Sim");
		return returnOrdersHistory;
	}

	ReturnOrdersHistory createReturnOrdersHistoryDiscartNot() {
		ReturnOrdersHistory returnOrdersHistory = new ReturnOrdersHistory();
		returnOrdersHistory.setIdReturnOrdersHistory(1L);
		returnOrdersHistory.setIdPurchaseOrder(createPurchaseOrder().getId());
		returnOrdersHistory.setReturnOrders(createReturnOrders());
		returnOrdersHistory.setDiscard("Sim");
		return returnOrdersHistory;
	}

	List<ReturnOrdersHistory> createListReturnOrdersHistory() {
		List<ReturnOrdersHistory> list = new ArrayList<>();
		list.add(createReturnOrdersHistoryDiscartYes());
		list.add(createReturnOrdersHistoryDiscartNot());
		return list;
	}

	List<ReturnOrdersHistory> createFailListReturnOrdersHistory() {
		List<ReturnOrdersHistory> list = new ArrayList<>();
		list.add(createFailReturnOrdersHistoryDiscart());
		return list;
	}
	
	ReturnOrdersHistoryDTO createReturnOrdersHistoryDTOYes() {
		ReturnOrdersHistoryDTO dto = new ReturnOrdersHistoryDTO();
		dto.setIdPurchaseOrder(createPurchaseOrder().getId());
		dto.setIdReturnOrders(createReturnOrders().getIdReturnOrders());
		dto.setDiscard("Sim");
		return dto;
	}
	
	ReturnOrdersHistoryDTO createReturnOrdersHistoryDTONot() {
		ReturnOrdersHistoryDTO dto = new ReturnOrdersHistoryDTO();
		dto.setIdPurchaseOrder(createPurchaseOrder().getId());
		dto.setIdReturnOrders(createReturnOrders().getIdReturnOrders());
		dto.setDiscard("Não");
		return dto;
	}
	
//	Optional<ReturnOrdersHistory> createOptionalReturnOrdersHistory() {
//		ReturnOrdersHistory returnOrdersHistory = new ReturnOrdersHistory();
//		returnOrdersHistory.setIdReturnOrdersHistory(null);
//		returnOrdersHistory.setIdPurchaseOrder(createPurchaseOrder().getId());
//		returnOrdersHistory.setReturnOrders(createReturnOrders());
//		returnOrdersHistory.setDiscard("Sim");
//		return null;
//
//	}



	@Test
	void testListReturnOrdersHistory() {

		createListReturnOrdersHistory();

		Mockito.when(mockReturnOrdersHistoryRepository.findAll()).thenReturn(createListReturnOrdersHistory());
		returnOrdersHistoryService.listReturnOrdersHistory();

		assertNotNull(createListReturnOrdersHistory());

	}

	@Test
	void testFindReturnOrdersHistoryByIdNoSuccess() {

		createFailListReturnOrdersHistory();

		Mockito.when(mockReturnOrdersHistoryRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(createListReturnOrdersHistory().get(0)));

		Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
			returnOrdersHistoryService.findReturnOrdersHistoryById(createFailListReturnOrdersHistory().get(0).getIdReturnOrdersHistory());
		});

		assertTrue(exceptionThatWasThrown.getMessage(), true);	
	}

	@Test
	void testFindReturnOrdersHistoryByIdWithSuccess() throws Exception {

		createListReturnOrdersHistory();

		Mockito.when(mockReturnOrdersHistoryRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(createListReturnOrdersHistory().get(0)));

		returnOrdersHistoryService.findReturnOrdersHistoryById(createListReturnOrdersHistory().get(1).getIdPurchaseOrder());

		assertNotNull(createListReturnOrdersHistory().get(0).getIdReturnOrdersHistory());

		assertEquals(1L, createListReturnOrdersHistory().get(0).getIdReturnOrdersHistory());
	}

	@Test
	void testGetIdReturnOrders() {

		createReturnOrdersHistoryDTOYes();

		returnOrdersHistoryService.getIdReturnOrders(createReturnOrdersHistoryDTOYes());

		assertNotNull(createReturnOrders());
	}














}