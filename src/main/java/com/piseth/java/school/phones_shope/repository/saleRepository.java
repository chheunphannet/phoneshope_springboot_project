package com.piseth.java.school.phones_shope.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phones_shope.entity.Sale;
@Repository
public interface saleRepository extends JpaRepository<Sale, Integer>{
}
