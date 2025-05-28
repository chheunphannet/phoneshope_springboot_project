package com.piseth.java.school.phones_shope.service.MapperImpl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.Mapper.ExpendReportDtoMapper;
import com.piseth.java.school.phones_shope.reportDto.expendReportDTO;

@Service
public class expendReportDtoMapperImpl implements ExpendReportDtoMapper{

	@Override
	public expendReportDTO  toListExpendReportDTO(Integer id, String name, Integer sumUnit, BigDecimal sumAmount) {
		expendReportDTO ep = new expendReportDTO();
		ep.setProductId(id);
		ep.setProductName(name);
		ep.setUnit(sumUnit);
		ep.setTotalAmount(sumAmount);
		return ep;
	}
	
}
