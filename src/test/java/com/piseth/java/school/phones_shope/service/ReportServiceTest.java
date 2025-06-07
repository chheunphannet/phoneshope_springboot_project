package com.piseth.java.school.phones_shope.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import com.piseth.java.school.phones_shope.Mapper.ExpendReportDtoMapper;
import com.piseth.java.school.phones_shope.entity.ProductImportHistory;
import com.piseth.java.school.phones_shope.reportDto.expendReportDTO;
import com.piseth.java.school.phones_shope.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phones_shope.repository.saleDetalRepository;
import com.piseth.java.school.phones_shope.repository.saleRepository;
import com.piseth.java.school.phones_shope.service.imp.reportServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
	private reportService reportService;
	@Mock
	private saleRepository repository;
	@Mock
	private saleDetalRepository detalRepository;
	@Mock
	private ProductImportHistoryRepository proImHisRepository;
	@Mock
	private ExpendReportDtoMapper expendReportDtoMapper;
	
	@BeforeEach
	void setUp() {
		reportService = new reportServiceImpl(
				repository, detalRepository, proImHisRepository, expendReportDtoMapper);
	}
	
	@Test
    void testGetExpenseReport() {
        // Given
        List<ProductImportHistory> importHistory = ReportTestHelper.getProductImportHistory();

        when(proImHisRepository.findAll(any(Specification.class))).thenReturn(importHistory);

        // Use thenAnswer to dynamically return expendReportDTO with passed values
        when(expendReportDtoMapper.toListExpendReportDTO(anyInt(), anyString(), anyInt(), any(BigDecimal.class)))
            .thenAnswer(inv -> new expendReportDTO(
                inv.getArgument(0),
                inv.getArgument(1),
                inv.getArgument(2),
                inv.getArgument(3)
            ));

        // When
        List<expendReportDTO> result = reportService.getProductImportByDate(
            LocalDate.of(2025, 5, 20),
            LocalDate.of(2025, 5, 25)
        );

        // Then
        assertEquals(2, result.size());

     // 🧪 Check content of each DTO
        expendReportDTO dto1 = result.get(0);
        assertEquals(1, dto1.getProductId());
        assertEquals("IPHONE 13", dto1.getProductName());
        assertEquals(15, dto1.getUnit());
        assertEquals(BigDecimal.valueOf(7500.0), dto1.getTotalAmount());

        expendReportDTO dto2 = result.get(1);
        assertEquals(2, dto2.getProductId());
        assertEquals("IPHONE 13 PRO", dto2.getProductName());
        assertEquals(5, dto2.getUnit());
        assertEquals(BigDecimal.valueOf(2500.0), dto2.getTotalAmount());
    }


}
