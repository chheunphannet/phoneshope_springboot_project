package com.piseth.java.school.phones_shope.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "Product", 
uniqueConstraints = {@UniqueConstraint(columnNames = {"color_id","model_id","brand_id"})}
)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "product_name", nullable = false, length = 50, unique = true)
	private String name;
	@Column(name = "available_unit")
	private Integer availableUnit;
	@Column(name = "sale_price")
	private BigDecimal salePrice;
	@Column(name = "image_path")
	private String imagePath;
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;
	@ManyToOne
	@JoinColumn(name = "model_id")
	private Model model;
	
	
	
}
