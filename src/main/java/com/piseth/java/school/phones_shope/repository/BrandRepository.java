package com.piseth.java.school.phones_shope.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phones_shope.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
	List<Brand> findByNameContainingIgnoreCase(String name);
	    
	}

	
	
