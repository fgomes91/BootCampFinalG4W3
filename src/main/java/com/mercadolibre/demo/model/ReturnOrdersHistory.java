package com.mercadolibre.demo.model;

import java.io.Serializable;

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
@Table(name = "return_orders_history")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReturnOrdersHistory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idreturn_orders_history")
	private Long idReturnOrdersHistory;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idreturn_orders", nullable = false)
	private ReturnOrders returnOrders;
	
    @Column(name = "discard", nullable = false , length=3)
	private String discard;
    
    @Column(name = "idpurchase_order", nullable = false)
 	private Long idPurchaseOrder;  
}
