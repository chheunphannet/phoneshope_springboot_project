package com.piseth.java.school.phones_shope.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.piseth.java.school.phones_shope.DTO.ModelDTO;
import com.piseth.java.school.phones_shope.entity.Model;
import com.piseth.java.school.phones_shope.service.BrandService;

@Mapper(componentModel = "spring", uses = BrandService.class)
public interface ModelMapper {

    @Mapping(target = "brand", source = "brand_id") //brand -> id
    Model toModel(ModelDTO modelDTO);

    @Mapping(target = "brand_id", source = "model.model_id")
    ModelDTO toModelDto(Model model);

}
