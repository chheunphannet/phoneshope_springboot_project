package com.piseth.java.school.phones_shope.service.imp;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.entity.Product;
import com.piseth.java.school.phones_shope.repository.ProductRepository;
import com.piseth.java.school.phones_shope.service.ProductServie;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ProductServieImpl implements ProductServie{
	private final ProductRepository productRepository;
	@Override
	public Product createProduct(Product product) {
		String name = "%s %s"
				.formatted(
						product.getModel().getName(),
						product.getColor().getName()
						);
		product.setName(name);
		return productRepository.save(product);
	}
 
}
