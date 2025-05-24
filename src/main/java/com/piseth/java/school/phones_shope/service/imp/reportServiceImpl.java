package com.piseth.java.school.phones_shope.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.DTO.ProductReportDTO;
import com.piseth.java.school.phones_shope.projection.ProductSold;
import com.piseth.java.school.phones_shope.repository.saleRepository;
import com.piseth.java.school.phones_shope.service.reportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class reportServiceImpl implements reportService {
	private final saleRepository repository;

	@Override
	public List<ProductSold> getProductSold(LocalDate start, LocalDate end) {
		return repository.findProductSold(start, end);
	}

	@Override
	public List<ProductReportDTO> getProductSoldByDate(LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub
		return null; 
	}

}
