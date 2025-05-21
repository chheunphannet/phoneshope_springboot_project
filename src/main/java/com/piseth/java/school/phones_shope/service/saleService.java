package com.piseth.java.school.phones_shope.service;

import java.util.Map;

import com.piseth.java.school.phones_shope.DTO.saleDTO;
import com.piseth.java.school.phones_shope.entity.Product;
import com.piseth.java.school.phones_shope.entity.Sale;

public interface saleService {
	Map<Integer, Product> validate(saleDTO dto);
	Sale saveSale(saleDTO dto);
	void saveSaleDetail(saleDTO dto, Sale sale, Map<Integer, Product> productMap); 
	void sell(saleDTO dto);
	Sale findById(Integer id);
	void cancelSale(Integer saleId);
}
