package com.piseth.java.school.phones_shope.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phones_shope.DTO.BrandDTO;
import com.piseth.java.school.phones_shope.entity.Brand;
import com.piseth.java.school.phones_shope.service.BrandService;
import com.piseth.java.school.phones_shope.util.Mapper;

@RestController
@RequestMapping("/brands")
public class BrandController {
	@Autowired
	private BrandService brandService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		Brand brand = Mapper.toEntity(brandDTO);
		return ResponseEntity.ok(brandService.save(brand)); 
	} 
	
	@GetMapping("{id}")
	public ResponseEntity<?> getBrand(@PathVariable Integer id) {
		Brand brand = brandService.getByID(id);
		return ResponseEntity.ok(brand); 
	}
}
