package com.piseth.java.school.phones_shope.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.piseth.java.school.phones_shope.DTO.PriceDTO;
import com.piseth.java.school.phones_shope.DTO.ProductDTO;
import com.piseth.java.school.phones_shope.DTO.ProductImportDTO;
import com.piseth.java.school.phones_shope.Mapper.ProductMapper;
import com.piseth.java.school.phones_shope.entity.Product;
import com.piseth.java.school.phones_shope.service.ProductServie;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
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
	
	@PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> isExelFile(@RequestParam MultipartFile file){
		boolean exelFile = productServie.isExelFile(file);
		return ResponseEntity.ok("is exel file :" + exelFile);
	}
	
	@PostMapping(value = "/uploadProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadProductsExelFile(@RequestParam MultipartFile file){
		List<ProductImportDTO> uploadProduct = productServie.uploadProduct(file);
		return ResponseEntity.ok(uploadProduct);
	}

}
