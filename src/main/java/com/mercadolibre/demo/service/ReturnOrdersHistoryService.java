package com.mercadolibre.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.demo.dto.ReturnOrdersHistoryDTO;
import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.ReturnOrders;
import com.mercadolibre.demo.model.ReturnOrdersHistory;
import com.mercadolibre.demo.repository.BatchStockRepository;
import com.mercadolibre.demo.repository.ItemOfProductRepository;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import com.mercadolibre.demo.repository.ReturnOrdersHistoryRepository;
import com.mercadolibre.demo.repository.ReturnOrdersRepository;

@Service
public class ReturnOrdersHistoryService {
	
	private ReturnOrdersHistoryRepository returnOrdersHistoryRepository; 
	private ReturnOrdersRepository returnOrdersRepository;
	private BatchStockRepository batchStockRepository;
	private PurchaseOrderRepository purchaseOrderRepository;
	private ItemOfProductRepository itemOfProductRepository;
	
	
	@Autowired
	public ReturnOrdersHistoryService(ReturnOrdersHistoryRepository returnOrdersHistoryRepository,
			ReturnOrdersRepository returnOrdersRepository, BatchStockRepository batchStockRepository,
			PurchaseOrderRepository purchaseOrderRepository, ItemOfProductRepository itemOfProductRepository) {
		this.returnOrdersHistoryRepository = returnOrdersHistoryRepository;
		this.returnOrdersRepository = returnOrdersRepository;
		this.batchStockRepository = batchStockRepository;
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.itemOfProductRepository = itemOfProductRepository;
	}

	public ReturnOrdersHistory toDiscard(ReturnOrdersHistoryDTO dto) throws Exception {
		if (dto.getDiscard().equals("Sim")) {
			ReturnOrdersHistory returnOrdersHistory = convertReturnOrdersHistoryToDTO(dto);
			return returnOrdersHistoryRepository.save(returnOrdersHistory);
	
		} else {
			throw new Exception("Discard deve ser igual a Sim, pois o produto sera descartado ");
		}
	}
	
	public ReturnOrdersHistory incrementInBatchStock(ReturnOrdersHistoryDTO dto) throws Exception {
		if (dto.getDiscard().equals("Não")) {
			ReturnOrdersHistory returnOrdersHistory = convertReturnOrdersHistoryToDTO(dto);
			productsToBatchStock(dto.getIdPurchaseOrder());
			return returnOrdersHistoryRepository.save(returnOrdersHistory);
		} else {
			throw new Exception("Discard deve ser igual a Não, pois o produto sera devolvido ao estoque");
		}
	}
	
	public List<ReturnOrdersHistory> listReturnOrdersHistory() {
		return returnOrdersHistoryRepository.findAll();
	}
	
	public List<ReturnOrdersHistory> findReturnOrdersHistoryById(Long id) throws Exception {
		if(returnOrdersHistoryRepository.findById(id).isPresent()) {
			return returnOrdersHistoryRepository.listId(id);
		} else {
			throw new Exception("Id não cadastrado");
		}
	}
	
    public void incrementQuantity(ItemOfProduct item, List<BatchStock> batchStockList) throws Exception {
        for (BatchStock batchStock : batchStockList) {
            batchStock.setCurrentQuantity(batchStock.getCurrentQuantity() + item.getQuantity());
            batchStockRepository.saveAndFlush(batchStock);
        }
    }
	
    public List<ItemOfProduct> productsToBatchStock(Long id) throws Exception {

        List<ItemOfProduct> list = itemOfProductRepository.orderOfItem(id);

        if (purchaseOrderRepository.findById(id).isPresent()) {
        	 for (ItemOfProduct item : list) {
                 List<BatchStock> batchStockList = batchStockRepository.batchStockList(item.getSalesAd().getId());
                 if (batchStockList != null) {
                     incrementQuantity(item, batchStockList);
                     item.setQuantity(0L);
                     itemOfProductRepository.saveAndFlush(item);
                 }
             }
             return list;
        } else {
            throw new Exception("Id do carrinho não cadastrado");
        }
    }
    	
	public Optional<ReturnOrders> getIdReturnOrders(ReturnOrdersHistoryDTO dto) {
		Optional<ReturnOrders> id = returnOrdersRepository.findById(dto.getIdReturnOrders());
		return id;
	}

	public ReturnOrdersHistory convertReturnOrdersHistoryToDTO(ReturnOrdersHistoryDTO dto) throws Exception {
		if(getIdReturnOrders(dto).isPresent()) {
			ReturnOrdersHistory returnOrdersHistory = new ReturnOrdersHistory();
			returnOrdersHistory.setReturnOrders(getIdReturnOrders(dto).get());
			returnOrdersHistory.setDiscard(dto.getDiscard());
			returnOrdersHistory.setIdPurchaseOrder(dto.getIdPurchaseOrder());
			return returnOrdersHistory;
		} else {
			throw new Exception("Id de ReturnOrders não está cadastrado");
		}
	}
}
