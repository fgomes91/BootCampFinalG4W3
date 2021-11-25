package com.mercadolibre.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReturnOrdersDTO {
	
	@NotNull(message = "{id.not.null}")
	private Long idItemOfProduct;
		
	@NotBlank(message = "{reason.not.blank}")
	private String reason;
    
	@NotBlank(message = "{damage.not.blank}")
	private String damage;
}
