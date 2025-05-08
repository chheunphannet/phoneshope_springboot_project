package com.piseth.java.school.phones_shope.service.imp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.DTO.ProductImportDTO;
import com.piseth.java.school.phones_shope.ExceptionHandle.ResourceNotFoundException;
import com.piseth.java.school.phones_shope.Mapper.ProductMapper;
import com.piseth.java.school.phones_shope.entity.Product;
import com.piseth.java.school.phones_shope.entity.ProductImportHistory;
import com.piseth.java.school.phones_shope.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phones_shope.repository.ProductRepository;
import com.piseth.java.school.phones_shope.service.ProductServie;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServieImpl implements ProductServie {
	private final ProductRepository productRepository;
	private final ProductMapper ProductMapper;
	private final ProductImportHistoryRepository productHistoryRepo;

	@Override
	public Product createProduct(Product product) {
		String name = "%s %s".formatted(product.getModel().getName(), product.getColor().getName());
		product.setName(name);
		return productRepository.save(product);
	}

	@Override
	public Product getbyId(Integer id) {
		return productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("not found this brand, id : " + id, HttpStatus.NOT_FOUND));
	}

	@Override
	public void importProduct(ProductImportDTO dto) {
		//import product
		Product product = this.getbyId(dto.getProductId());
		product.setAvailableUnit(product.getAvailableUnit() + dto.getImportUnit());
		productRepository.save(product);
		//save to product history
		ProductImportHistory productHistory = ProductMapper.toProductIpmortHistory(dto);
		productHistory.setProduct(product);
		productHistoryRepo.save(productHistory);
	}

}
