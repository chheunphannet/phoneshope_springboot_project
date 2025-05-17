package com.piseth.java.school.phones_shope.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phones_shope.DTO.PriceDTO;
import com.piseth.java.school.phones_shope.DTO.ProductDTO;
import com.piseth.java.school.phones_shope.DTO.ProductImportDTO;
import com.piseth.java.school.phones_shope.Mapper.ProductMapper;
import com.piseth.java.school.phones_shope.entity.Product;
import com.piseth.java.school.phones_shope.service.ProductServie;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
	private final ProductServie productServie;
	private final ProductMapper productmapper;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDTO dto) {
		Product product = productmapper.toProduct(dto);
		return ResponseEntity.ok(productServie.createProduct(product));
	}

	@PostMapping("/importProduct")
	public ResponseEntity<?> importProduct(@RequestBody ProductImportDTO dto) {
		productServie.importProduct(dto);
		return ResponseEntity.ok().build();
	}

	@PostMapping("{productId}/setSalePrice")
	public ResponseEntity<?> setSalePrice(@PathVariable Integer productId,@RequestBody PriceDTO dto) {
		productServie.setSalePrice(productId, dto.getPrice());
		return ResponseEntity.ok().build(); 
	}

}
