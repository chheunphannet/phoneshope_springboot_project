package com.piseth.java.school.phones_shope.service.MapperImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.DTO.saleDTO;
import com.piseth.java.school.phones_shope.Mapper.saleDetailMapper;
import com.piseth.java.school.phones_shope.entity.Product;
import com.piseth.java.school.phones_shope.entity.Sale;
import com.piseth.java.school.phones_shope.entity.SaleDetail;
import com.piseth.java.school.phones_shope.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class saleDetailMapperImpl implements saleDetailMapper {
	private final ProductRepository productSeRepository;
	@Override
	public List<SaleDetail> toListSaleDetail(saleDTO dto, Sale sale, Map<Integer, Product> productMap) {
		return dto.getProducts().stream().map(ps -> {
			Product product = productMap.get(ps.getProductId());
			// Decrease stock
			int remaining = product.getAvailableUnit() - ps.getNumOfUnit();
			product.setAvailableUnit(remaining); // update in-memory, persist later
			SaleDetail detail = new SaleDetail();
			productSeRepository.save(product);
			detail.setProduct(product);
			detail.setSale(sale);
			detail.setUnit(ps.getNumOfUnit());
			detail.setAmount(product.getSalePrice());
			
			return detail;
		}).toList();
	}
}
