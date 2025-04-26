package com.piseth.java.school.phones_shope.service;

import java.util.List;

import com.piseth.java.school.phones_shope.ExceptionHandle.ResourceNotFoundException;
import com.piseth.java.school.phones_shope.entity.Brand;

public interface BrandService {
	Brand save(Brand brand);  
	Brand getByID(Integer id);
	Brand updateByID(Integer id, Brand brandUpdate);
	ResourceNotFoundException deleteByID(Integer id); 
	List<Brand> getListOfBands();
	List<Brand> getByName(String name);
}
