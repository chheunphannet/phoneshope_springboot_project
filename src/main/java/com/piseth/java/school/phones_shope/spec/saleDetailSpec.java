package com.piseth.java.school.phones_shope.spec;

import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;

import com.piseth.java.school.phones_shope.entity.Sale;
import com.piseth.java.school.phones_shope.entity.SaleDetail;
import com.piseth.java.school.phones_shope.entity.SaleDetail_;
import com.piseth.java.school.phones_shope.entity.Sale_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class saleDetailSpec implements Specification<SaleDetail> {
	private saleDetalFilter detalFilter;

//	@Override
//	public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder db) {
//		List<Predicate> predicate = new ArrayList<>();
//		Join<SaleDetail, Sale> sale = saleDetail.join("sale");
//
//		if (Objects.nonNull(detalFilter.getStart())) {
//		    predicate.add(db.greaterThanOrEqualTo(sale.get("soldDate"), detalFilter.getStart()));
//		}
//
//		if (Objects.nonNull(detalFilter.getEnd())) {
//		    predicate.add(db.lessThanOrEqualTo(sale.get("soldDate"), detalFilter.getEnd()));
//		}
//
//		Predicate pd = db.and(predicate.toArray(Predicate[]::new));
//
//		return pd;
//	}
	@Override
	public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder db) {
		Join<SaleDetail, Sale> sale = saleDetail.join(SaleDetail_.SALE);
		 return db.and(
				db.greaterThanOrEqualTo(sale.get(Sale_.SOLD_DATE), detalFilter.getStart().atStartOfDay()),
				db.lessThanOrEqualTo(sale.get(Sale_.SOLD_DATE), detalFilter.getEnd().atTime(LocalTime.MAX))
			);
	}

}
