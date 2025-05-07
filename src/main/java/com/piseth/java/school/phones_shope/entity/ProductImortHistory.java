package com.piseth.java.school.phones_shope.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class ProductImortHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "import_unit",nullable = false)
	private Integer importUnit;
	@Column(name = "price_per_unit",nullable = false)
	private BigDecimal pricePerUnit;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@Column(name = "import_Date",nullable = false)
	private LocalDateTime importDate;
}
