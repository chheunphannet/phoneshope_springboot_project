package com.piseth.java.school.phones_shope.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductImportDTO {
	@NotNull(message = "product id cannot null")
	private Integer productId;
	@Min(value = 0, message = "import unit must > 0")
	private Integer importUnit;
	@DecimalMin(value = "0.001")
	private BigDecimal pricePerUnit;
	private LocalDateTime importDate;
}
