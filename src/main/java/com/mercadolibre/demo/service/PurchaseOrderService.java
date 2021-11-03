package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.dto.PurchaseOrderDTO;
import com.mercadolibre.demo.dto.PriceDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.BuyerRepository;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import com.mercadolibre.demo.repository.SalesAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PurchaseOrderService {

    private PurchaseOrderRepository purchaseOrderRepository;
    private BuyerRepository buyerRepository;
    private SalesAdRepository salesAdRepository;


    @Autowired
    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, BuyerRepository buyerRepository,
			SalesAdRepository salesAdRepository) {
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.buyerRepository = buyerRepository;
		this.salesAdRepository = salesAdRepository;
	}

	public PurchaseOrder save(PurchaseOrderDTO dto) throws Exception {
        PurchaseOrder purchaseOrder = convertPurchaseToDTO(dto);
        return purchaseOrderRepository.save(purchaseOrder);
    }


    public List<PurchaseOrder> list() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder convertPurchaseToDTO(PurchaseOrderDTO dto) throws Exception {
       PurchaseOrder purchaseOrder = new PurchaseOrder();
        Optional<Buyer> buyer = buyerRepository.findById(dto.getBuyer());
        purchaseOrder.setBuyer(buyer.get());
        if (buyer.isPresent()){
        List<ItemOfProduct> itemOfProducts = convertItemOfProduct(dto.getItemOfProduct(),purchaseOrder);
        purchaseOrder.setItemOfProduct(itemOfProducts);
            return purchaseOrder;
        }
        throw new Exception("Erro no carrinho");
    }


    public Optional<PurchaseOrder> findById(Long id) {
        return purchaseOrderRepository.findById(id);
    }

    public PurchaseOrder update(PurchaseOrderDTO dto, Long id) throws Exception {
        Optional<PurchaseOrder> existPurchaseOrder = findById(id);
        if (existPurchaseOrder.isPresent()) {
        	PurchaseOrder purchaseOrder = convertPurchaseToDTO(dto);
            purchaseOrder.setId(id);
            return purchaseOrderRepository.saveAndFlush(purchaseOrder);
        } else {
            throw new Exception("Id nao casdastrado");
        }
    }


    public void delete(Long id) {
        purchaseOrderRepository.deleteById(id);
    }

    public List<ItemOfProduct> convertItemOfProduct(List<ItemOfProductDTO> dto,PurchaseOrder purchOrder) throws Exception {

       List<ItemOfProduct> listOfProducts = new ArrayList<>();
        for (ItemOfProductDTO item: dto){
            try {
                Optional<SalesAd> salesAd = salesAdRepository.findById(item.getSalesAd());
                if (salesAd.isPresent()){
                    ItemOfProduct product = new ItemOfProduct(item.getQuantity(),salesAd.get(),purchOrder);
                    listOfProducts.add(product);
                }

            }catch (Exception e){
                throw new Exception("Erro no item do produto");
            }
        }
        return listOfProducts;
    }

    public PriceDTO PriceLista(List<ItemOfProduct> lista) throws Exception {
        Double valor = 0.0;
        for (ItemOfProduct item: lista){
            valor += item.getSalesAd().getPrice() * item.getQuantity();
        }
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setTotalPrice(valor);
        return priceDTO;
    }
}