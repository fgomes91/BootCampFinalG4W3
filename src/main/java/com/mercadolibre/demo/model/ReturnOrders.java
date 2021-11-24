package com.mercadolibre.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class ReturnOrders implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idreturn_orders")
	private Long idReturnOrders;

    @Column(name = "iditem_ofproduct", nullable = false)
	private Long idItemOfProduct;
	
    @Column(name = "return_code", nullable = false)
	private String returnCode;
	
    @Column(name = "reason", nullable = false)
	private String reason;
    
    @Column(name = "damage", nullable = false)
	private String damage;
}
