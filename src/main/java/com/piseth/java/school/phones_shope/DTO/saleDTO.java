package com.piseth.java.school.phones_shope.DTO;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
@Data 
public class saleDTO {
	@NotEmpty
	private List<productsoldDTO> products;
	private LocalDateTime saleDate;
}
