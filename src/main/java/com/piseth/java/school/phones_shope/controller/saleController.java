package com.piseth.java.school.phones_shope.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phones_shope.DTO.saleDTO;
import com.piseth.java.school.phones_shope.service.saleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/sales")
public class saleController {
	private final saleService ServiceSale;
	@PostMapping
	public ResponseEntity<?> sellProducts(@RequestBody saleDTO dto){
		ServiceSale.sell(dto);
		return ResponseEntity.ok().build();
	}
}
