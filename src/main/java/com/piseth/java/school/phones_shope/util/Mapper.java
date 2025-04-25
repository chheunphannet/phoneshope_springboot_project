package com.piseth.java.school.phones_shope.util;

import com.piseth.java.school.phones_shope.DTO.BrandDTO;
import com.piseth.java.school.phones_shope.entity.Brand;

public class Mapper {
	public static Brand toEntity(BrandDTO dto){
		Brand brand = new Brand();
		brand.setId(dto.getId());
		brand.setName(dto.getName());
		return brand; 
	}
}
