package com.piseth.java.school.phones_shope.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.piseth.java.school.phones_shope.entity.Sale;
import com.piseth.java.school.phones_shope.entity.SaleDetail;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class saleDetailSpec implements Specification<SaleDetail> {
	private saleDetalFilter detalFilter;

	@Override
	public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder db) {
		List<Predicate> predicate = new ArrayList<>();
		Join<SaleDetail, Sale> sale = saleDetail.join("sale");
		
		if (Objects.nonNull(detalFilter.getStart())) {
			db.greaterThanOrEqualTo(sale.get("soldDate"), detalFilter.getStart());
		}
		 
		if (Objects.nonNull(detalFilter.getEnd())) {
			db.lessThanOrEqualTo(sale.get("soldDate"), detalFilter.getEnd());
		}
		
		Predicate pd = db.and(predicate.toArray(Predicate[]::new));
		
		return pd;
	}
}
