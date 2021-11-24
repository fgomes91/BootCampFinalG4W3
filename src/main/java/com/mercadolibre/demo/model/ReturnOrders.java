package com.mercadolibre.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "return_orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReturnOrders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idreturn_orders")
	private Long idReturnOrders;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idpurchase_order", nullable = false)
	private PurchaseOrder purchaseOrder;
	
    @Column(name = "reason", nullable = false)
	private String reason;
    
    @Column(name = "damage", nullable = false)
	private String damage;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idbuyer_data", nullable = false)
	private BuyerData buyerData;
	
    @Column(name = "company_address", nullable = false)
	private String companyAddress;
}
