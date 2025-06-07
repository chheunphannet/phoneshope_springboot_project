package com.piseth.java.school.phones_shope.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.piseth.java.school.phones_shope.entity.Product;
import com.piseth.java.school.phones_shope.entity.ProductImportHistory;

public class ReportTestHelper {
	private static Product pro1 = Product.builder()
			.id(1)
			.name("IPHONE 13")
			.build();
	
	private static Product pro2 = Product.builder()
			.id(2)
			.name("IPHONE 13 PRO")
			.build();
	
	private static Product pro3 = Product.builder()
			.id(3)
			.name("IPHONE 13 PRO MAX")
			.build();
	
	public static List<ProductImportHistory> getProductImportHistory(){
		ProductImportHistory h1 = ProductImportHistory.builder()
				.product(pro1)
				.importUnit(10)
				.pricePerUnit(BigDecimal.valueOf(500))
				.importDate(LocalDateTime.of(2025, 05, 21 , 12, 00))
				.build();
		
		ProductImportHistory h2 = ProductImportHistory.builder()
				.product(pro2)
				.importUnit(5)
				.pricePerUnit(BigDecimal.valueOf(500))
				.importDate(LocalDateTime.of(2025, 05, 22 , 12, 00))
				.build();
		
		ProductImportHistory h3 = ProductImportHistory.builder()
				.product(pro1)
				.importUnit(5)
				.pricePerUnit(BigDecimal.valueOf(500))
				.importDate(LocalDateTime.of(2025, 05, 23 , 12, 00))
				.build();
		
		return List.of(h1,h2,h3);
	}
}
