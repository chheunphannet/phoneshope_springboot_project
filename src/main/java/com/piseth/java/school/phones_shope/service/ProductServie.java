package com.piseth.java.school.phones_shope.service;

import com.piseth.java.school.phones_shope.DTO.ProductImportDTO;
import com.piseth.java.school.phones_shope.entity.Product;

public interface ProductServie{
	Product createProduct(Product product);
	Product getbyId(Integer id);
	void importProduct(ProductImportDTO dto);
}
