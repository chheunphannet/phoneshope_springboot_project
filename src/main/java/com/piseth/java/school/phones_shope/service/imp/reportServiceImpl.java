package com.piseth.java.school.phones_shope.service.imp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.DTO.ProductReportDTO;
import com.piseth.java.school.phones_shope.Mapper.ExpendReportDtoMapper;
import com.piseth.java.school.phones_shope.entity.Product;
import com.piseth.java.school.phones_shope.entity.ProductImportHistory;
import com.piseth.java.school.phones_shope.entity.SaleDetail;
import com.piseth.java.school.phones_shope.projection.ProductSold;
import com.piseth.java.school.phones_shope.reportDto.expendReportDTO;
import com.piseth.java.school.phones_shope.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phones_shope.repository.saleDetalRepository;
import com.piseth.java.school.phones_shope.repository.saleRepository;
import com.piseth.java.school.phones_shope.service.reportService;
import com.piseth.java.school.phones_shope.spec.expenseReportSpec;
import com.piseth.java.school.phones_shope.spec.saleDetailSpec;
import com.piseth.java.school.phones_shope.spec.saleDetalFilter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class reportServiceImpl implements reportService {
	private final saleRepository repository;
	private final saleDetalRepository detalRepository;
	private final ProductImportHistoryRepository proImHisRepository;
	private final ExpendReportDtoMapper expendReportDtoMapper;

	@Override
	public List<ProductSold> getProductSold(LocalDate start, LocalDate end) {
		return repository.findProductSold(start, end);
	}

	@Override
	public List<ProductReportDTO> getProductSoldByDate(LocalDate start, LocalDate end) {
		List<ProductReportDTO> list = new ArrayList<>();

		// Step 1: Filter sale details by date
		saleDetalFilter detailFilter = new saleDetalFilter();
		detailFilter.setStart(start);
		detailFilter.setEnd(end);

		Specification<SaleDetail> spec = new saleDetailSpec(detailFilter);
		List<SaleDetail> saleDetails = detalRepository.findAll(spec).stream().filter(s -> s.getSale().getActive())
				.toList();

		// Step 3: Group sale details by product
		Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream() // some product have more saleDetail
				.collect(Collectors.groupingBy(SaleDetail::getProduct));

		// Step 4: Build ProductReportDTOs
		for (var entry : saleDetailMap.entrySet()) { // Each entry in the loop represents one Map.Entry<Product,
														// List<SaleDetail>>
			Product product = entry.getKey();
			List<SaleDetail> sdList = entry.getValue(); // key -> (Product) value -> (list of saleDetail)

			// Total units sold
			Integer unit = sdList.stream().map(SaleDetail::getUnit).reduce(0, Integer::sum);

			// Total amount = sum(unit * amount)
			double totalAmount = sdList.stream().mapToDouble(sd -> sd.getUnit() * sd.getAmount().doubleValue()).sum();

			// Create and populate DTO
			ProductReportDTO reportDTO = new ProductReportDTO();
			reportDTO.setProductId(product.getId());
			reportDTO.setProductName(product.getName());
			reportDTO.setUnit(unit);
			reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));

			list.add(reportDTO);
		}
		return list;
	}

	@Override
	public List<expendReportDTO> getProductImportByDate(LocalDate start, LocalDate end) {
		List<expendReportDTO> listExpendReportDTO = new ArrayList<>();
		
		List<ProductImportHistory> productImportList = proImHisRepository.findAll(expenseReportSpec.getProImHisByDate(start, end));
		
		Map<Product, List<ProductImportHistory>> productImportHistoryMap = productImportList.stream()
				.collect(Collectors.groupingBy(ProductImportHistory::getProduct));
		
		for (var entry : productImportHistoryMap.entrySet()) {
			Product pro = entry.getKey();
			List<ProductImportHistory> value = entry.getValue();

			Integer sumUnit = value.stream()
					.map(p -> p.getImportUnit())
					.reduce(0, Integer::sum);
			double sumPrice = value.stream()
					.mapToDouble(p -> p.getPricePerUnit().doubleValue() * p.getImportUnit())
					.sum();
			listExpendReportDTO.add(
				    expendReportDtoMapper.toListExpendReportDTO(pro.getId(), pro.getName(), sumUnit, BigDecimal.valueOf(sumPrice))
				);
		}
		return listExpendReportDTO;
	}

}
