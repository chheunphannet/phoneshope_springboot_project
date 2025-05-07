package com.piseth.java.school.phones_shope.entity;

import java.math.BigDecimal;

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
public class SaleDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;
	@ManyToOne
	@JoinColumn(name = "pruduct_id")
	private Product product;
	@Column(name = "amount",nullable = false)
	private BigDecimal amount;
	@Column(name = "unit",nullable = false)
	private Integer unit;
	
}
