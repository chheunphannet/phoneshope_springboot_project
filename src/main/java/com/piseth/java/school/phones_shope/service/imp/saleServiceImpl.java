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
import com.piseth.java.school.phones_shope.repository.ProductRepository;
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
	private final ProductRepository productRepository;
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
		sale.setActive(dto.isActive());
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

	@Override
	public Sale findById(Integer id) {
		return saleRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("not found this sale, id : " + id, HttpStatus.NOT_FOUND));
	}

	@Override
	public void cancelSale(Integer saleId) {
		Sale sale = findById(saleId);
		if(sale.isActive()) {
			List<SaleDetail> saleDetailList = saleDetalRepository.findBySaleId(saleId);
			Map<Integer, Product> products = saleDetailList.stream()
					.map(pd -> pd.getProduct())
					.collect(Collectors.toMap(Product::getId, Function.identity()));
			
			saleDetailList.forEach(t -> {
				Product product = products.get(t.getProduct().getId());
				product.setAvailableUnit(product.getAvailableUnit() + t.getUnit());
				sale.setActive(false);
				saleRepository.save(sale);
				productRepository.save(product);
			});
		}
		
	}
	
	
}
