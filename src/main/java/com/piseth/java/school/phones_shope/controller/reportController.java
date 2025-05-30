package com.piseth.java.school.phones_shope.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phones_shope.service.reportService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/report")
public class reportController {
	private final reportService reportService;

	@GetMapping("{start}/{end}")
	public ResponseEntity<?> findSoldProduct(@PathVariable LocalDate start,@PathVariable LocalDate end) {
		return ResponseEntity.ok(reportService.getProductSold(start, end));
	}
	
	@GetMapping("v2/{start}/{end}")
	public ResponseEntity<?> findSoldProductv2(@PathVariable LocalDate start,@PathVariable LocalDate end) {
		return ResponseEntity.ok(reportService.getProductSoldByDate(start, end));
	}
	
	@GetMapping("ExpenseRepoty/{start}/{end}")
	public ResponseEntity<?> findProductImportByDate(@PathVariable LocalDate start,@PathVariable LocalDate end) {
		return ResponseEntity.ok(reportService.getProductImportByDate(start, end));
	}
	
}
