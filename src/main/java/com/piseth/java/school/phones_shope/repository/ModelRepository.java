package com.piseth.java.school.phones_shope.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phones_shope.entity.Model;
@Repository
public interface ModelRepository extends JpaRepository<Model, Integer>{
//	@Query("SELECT m FROM Model m WHERE m.brand.brand_id = :id")
//	List<Model> findByBrandId(@Param("id") Integer id);
	
	List<Model> findByBrandBrandId(Integer id);
	

}
