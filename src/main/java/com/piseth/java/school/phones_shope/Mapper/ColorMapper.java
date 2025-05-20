package com.piseth.java.school.phones_shope.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.piseth.java.school.phones_shope.DTO.ColorDTO;
import com.piseth.java.school.phones_shope.entity.Color;

@Mapper
public interface ColorMapper {
	ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);

	@Mapping(target = "colorId", ignore = true)
	Color toColor(ColorDTO dto);
}
