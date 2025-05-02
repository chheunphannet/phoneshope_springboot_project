package com.piseth.java.school.phones_shope.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.piseth.java.school.phones_shope.DTO.ModelDTO;
import com.piseth.java.school.phones_shope.entity.Model;
import com.piseth.java.school.phones_shope.service.BrandService;

@Mapper(componentModel = "spring", uses = BrandService.class)
public interface ModelMapper {
	
	ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    @Mapping(target = "brand", source = "brand_id") //brand -> id
    Model toModel(ModelDTO modelDTO);

    @Mapping(target = "model_id", source = "model.model_id")
    @Mapping(target = "brand_id", ignore = true)
    @Mapping(target = "brandName", source = "brand.name")
    ModelDTO toModelDto(Model model);
    
    List<ModelDTO> toListDto(List<Model> model);

}
