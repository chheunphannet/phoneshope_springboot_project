package com.piseth.java.school.phones_shope.spec;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;

import com.piseth.java.school.phones_shope.entity.ProductImportHistory;
import com.piseth.java.school.phones_shope.entity.ProductImportHistory_;

public class expenseReportSpec {
	public static Specification<ProductImportHistory> getProImHisByDate(LocalDate start, LocalDate end) {
		return (root, query, cb) -> cb.and(cb.greaterThanOrEqualTo(root.get(ProductImportHistory_.IMPORT_DATE), start.atStartOfDay()),
				cb.lessThanOrEqualTo(root.get(ProductImportHistory_.IMPORT_DATE), end.atTime(LocalTime.MAX)));
	}
} 
