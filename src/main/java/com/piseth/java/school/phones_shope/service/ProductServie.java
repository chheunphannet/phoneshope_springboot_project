package com.piseth.java.school.phones_shope.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.piseth.java.school.phones_shope.DTO.ProductImportDTO;
import com.piseth.java.school.phones_shope.entity.Product;

public interface ProductServie{
	Product createProduct(Product product);
	Product getbyId(Integer id);
	void importProduct(ProductImportDTO dto);
	void setSalePrice(Integer id, BigDecimal price);
	List<ProductImportDTO> uploadProduct(MultipartFile file);
	boolean isExelFile(MultipartFile file);
	Product findByModel_ModelIdAndColor_ColorId(Integer modelId, Integer colorId);
}
