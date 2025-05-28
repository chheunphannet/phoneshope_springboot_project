package com.piseth.java.school.phones_shope.Mapper;

import java.math.BigDecimal;
import java.util.List;

import com.piseth.java.school.phones_shope.reportDto.expendReportDTO;

public interface ExpendReportDtoMapper {
	expendReportDTO toListExpendReportDTO(Integer id, String name, Integer sumUnit, BigDecimal sumAmount);
}
