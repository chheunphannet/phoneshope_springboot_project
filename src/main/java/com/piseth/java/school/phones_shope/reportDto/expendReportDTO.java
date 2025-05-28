package com.piseth.java.school.phones_shope.reportDto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class expendReportDTO {
	private Integer productId;
	private String productName;
	private Integer unit;
	private BigDecimal totalAmount;
}
