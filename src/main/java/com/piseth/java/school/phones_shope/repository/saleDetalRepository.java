package com.piseth.java.school.phones_shope.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phones_shope.entity.Sale;
import com.piseth.java.school.phones_shope.entity.SaleDetail;
@Repository
public interface saleDetalRepository extends JpaRepository<SaleDetail, Integer>{
	List<SaleDetail> findBySaleId(Integer id);
}
