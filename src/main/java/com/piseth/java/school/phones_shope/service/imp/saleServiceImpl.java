package com.piseth.java.school.phones_shope.service.imp;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.DTO.productsoldDTO;
import com.piseth.java.school.phones_shope.DTO.saleDTO;
import com.piseth.java.school.phones_shope.ExceptionHandle.ResourceNotFoundException;
import com.piseth.java.school.phones_shope.Mapper.saleDetailMapper;
import com.piseth.java.school.phones_shope.entity.Product;
import com.piseth.java.school.phones_shope.entity.Sale;
import com.piseth.java.school.phones_shope.entity.SaleDetail;
import com.piseth.java.school.phones_shope.repository.saleDetalRepository;
import com.piseth.java.school.phones_shope.repository.saleRepository;
import com.piseth.java.school.phones_shope.service.ProductServie;
import com.piseth.java.school.phones_shope.service.saleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class saleServiceImpl implements saleService {
	private final saleRepository saleRepository;
	private final ProductServie productServie;
	private final saleDetalRepository saleDetalRepository;
	private final saleDetailMapper detailMapper;
	@Override
	public Map<Integer, Product> validate(saleDTO dto) {
		// productSold product id, qt of product
		Map<Integer, Product> productMap = dto.getProducts().stream()
				.map(ps -> productServie.getbyId(ps.getProductId())) // find product by id
				.collect(Collectors.toMap(Product::getId, Function.identity()));

		for (productsoldDTO ps : dto.getProducts()) { // products -> list<productsoldDTO>
			Product product = productMap.get(ps.getProductId());
			if (ps.getNumOfUnit() > product.getAvailableUnit()) {
				throw new ResourceNotFoundException("Product '%s' has only %d units available, but %d were requested."
						.formatted(product.getName(), product.getAvailableUnit(), ps.getNumOfUnit()),
						HttpStatus.BAD_REQUEST);
			}
		}
		return productMap;
	}

	@Override
	public Sale saveSale(saleDTO dto) {
		Sale sale = new Sale();
		sale.setSoldDate(dto.getSaleDate());
		return saleRepository.save(sale);
	}

	@Override
	public void saveSaleDetail(saleDTO dto, Sale sale, Map<Integer, Product> productMap) {
		List<SaleDetail> listSaleDetail = detailMapper.toListSaleDetail(dto, sale, productMap);
		saleDetalRepository.saveAll(listSaleDetail);
	}

	@Override
	public void sell(saleDTO dto) {
		//
		Map<Integer, Product> validate = validate(dto);
		//
		Sale sale = saveSale(dto);
		//
		saveSaleDetail(dto,sale,validate);
		
	}
	


//	@Override
//	public void validate(saleDTO dto) { // dto product id, qt of product
//		dto.getProducts().forEach(ps -> { // each is productSold
//			Product product = productServie.getbyId(ps.getProductId());
//			if (product.getAvailableUnit() > ps.getNumOfUnit()) {
//				throw new ResourceNotFoundException(
//						"product %s is less than product to buy".formatted(product.getName()), HttpStatus.BAD_REQUEST);
//			}
//		});
//	}

}
