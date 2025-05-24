package com.piseth.java.school.phones_shope.DTO;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class ProductReportDTO {
	private Long productId;
	private String productName;
	private Integer unit;
	private BigDecimal totalAmount;
}
