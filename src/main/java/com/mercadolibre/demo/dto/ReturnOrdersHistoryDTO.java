package com.mercadolibre.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReturnOrdersHistoryDTO {
	
	@NotNull(message = "{id.not.null}")
	private Long idReturnOrders;
	
	@NotBlank(message = "{discard.not.blank}")
	private String discard;
	
	@NotNull(message = "{id.not.null}")
 	private Long idPurchaseOrder;
}
