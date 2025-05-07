package com.piseth.java.school.phones_shope.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.piseth.java.school.phones_shope.DTO.ProductDTO;
import com.piseth.java.school.phones_shope.entity.Product;
import com.piseth.java.school.phones_shope.service.BrandService;
import com.piseth.java.school.phones_shope.service.ColorService;
import com.piseth.java.school.phones_shope.service.ModelService;

@Mapper(componentModel = "spring", uses = {ModelService.class,ColorService.class,BrandService.class})
public interface ProductMapper {
	@Mapping(target = "model", source  = "model_id")
	@Mapping(target = "color", source = "color_id")
	Product toProduct(ProductDTO product);
}
