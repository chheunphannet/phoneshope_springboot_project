package com.piseth.java.school.phones_shope.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phones_shope.entity.SaleDetail;

@Repository
public interface saleDetalRepository extends JpaRepository<SaleDetail, Integer>, JpaSpecificationExecutor<SaleDetail> {
	List<SaleDetail> findBySaleId(Integer id);
}
