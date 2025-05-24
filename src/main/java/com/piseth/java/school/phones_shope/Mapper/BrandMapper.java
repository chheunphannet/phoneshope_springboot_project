package com.piseth.java.school.phones_shope.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.piseth.java.school.phones_shope.DTO.BrandDTO;
import com.piseth.java.school.phones_shope.entity.Brand;
@Mapper
public interface BrandMapper {
	
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
	@Mapping(target = "brandId", ignore = true)
	Brand toBrand(BrandDTO brandDTO);
	BrandDTO toBrandDTO(Brand brand);
	List<BrandDTO> toListOfBrandDto(List<Brand> listOfBrand);
}
