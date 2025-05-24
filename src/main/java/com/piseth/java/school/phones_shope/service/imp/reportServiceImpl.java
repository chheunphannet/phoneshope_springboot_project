package com.piseth.java.school.phones_shope.service.imp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.DTO.ProductReportDTO;
import com.piseth.java.school.phones_shope.entity.Product;
import com.piseth.java.school.phones_shope.entity.SaleDetail;
import com.piseth.java.school.phones_shope.projection.ProductSold;
import com.piseth.java.school.phones_shope.repository.ProductRepository;
import com.piseth.java.school.phones_shope.repository.saleDetalRepository;
import com.piseth.java.school.phones_shope.repository.saleRepository;
import com.piseth.java.school.phones_shope.service.reportService;
import com.piseth.java.school.phones_shope.spec.saleDetailSpec;
import com.piseth.java.school.phones_shope.spec.saleDetalFilter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class reportServiceImpl implements reportService {
	private final saleRepository repository;
	private final saleDetalRepository detalRepository;
	private final ProductRepository productRepository;
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
	    List<SaleDetail> saleDetails = detalRepository.findAll(spec);
	    
	    // Step 2: Extract product IDs and map them to products
	    List<Integer> productIds = saleDetails.stream()
	        .map(sd -> sd.getProduct().getId())
	        .toList();

	    Map<Integer, Product> productMap = productRepository.findAllById(productIds).stream()
	        .collect(Collectors.toMap(Product::getId,Function.identity() ));
	    
	    // Step 3: Group sale details by product
	    Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream()
	        .collect(Collectors.groupingBy(SaleDetail::getProduct));

	    // Step 4: Build ProductReportDTOs
	    for (var entry : saleDetailMap.entrySet()) {
	        Product product = productMap.get(entry.getKey().getId());
	        List<SaleDetail> sdList = entry.getValue();

	        // Total units sold
	        Integer unit = sdList.stream()
	            .map(SaleDetail::getUnit)
	            .reduce(0, Integer::sum);

	        // Total amount = sum(unit * amount)
	        double totalAmount = sdList.stream()
	            .mapToDouble(sd -> sd.getUnit() * sd.getAmount().doubleValue())
	            .sum();

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


}
