package com.piseth.java.school.phones_shope.service;

import java.time.LocalDate;
import java.util.List;

import com.piseth.java.school.phones_shope.DTO.ProductReportDTO;
import com.piseth.java.school.phones_shope.projection.ProductSold;
import com.piseth.java.school.phones_shope.reportDto.expendReportDTO;

public interface reportService {
	List<ProductSold> getProductSold(LocalDate start, LocalDate end);
	List<ProductReportDTO> getProductSoldByDate(LocalDate start, LocalDate end);
	List<expendReportDTO> getProductImportByDate(LocalDate start, LocalDate end);
	
}
