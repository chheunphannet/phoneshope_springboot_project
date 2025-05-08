package com.piseth.java.school.phones_shope.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductImportDTO {
	private Integer productId;
	private Integer importUnit;
	private BigDecimal pricePerUnit;
	private LocalDateTime importDate;
}
