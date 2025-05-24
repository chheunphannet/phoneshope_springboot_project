package com.piseth.java.school.phones_shope.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phones_shope.entity.Sale;
import com.piseth.java.school.phones_shope.projection.ProductSold;

@Repository
public interface saleRepository extends JpaRepository<Sale, Integer> {
	@Query(value = """
	        SELECT p.id AS productId,
	               p.product_name AS productName,
	               SUM(sd.unit) AS unit,
	               SUM(sd.amount * sd.unit) AS totalAmount
	        FROM sale_detail sd
	        INNER JOIN sale s ON sd.sale_id = s.id
	        INNER JOIN product p ON sd.product_id = p.id
	        WHERE DATE(s.sale_date) BETWEEN :start AND :end
	              AND s.active = true
	        GROUP BY p.id, p.product_name
	        """, nativeQuery = true)
	List<ProductSold> findProductSold(LocalDate start, LocalDate end);
}
