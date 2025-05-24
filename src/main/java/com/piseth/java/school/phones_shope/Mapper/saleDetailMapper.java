package com.piseth.java.school.phones_shope.Mapper;

import java.util.List;
import java.util.Map;

import com.piseth.java.school.phones_shope.DTO.saleDTO;
import com.piseth.java.school.phones_shope.entity.Product;
import com.piseth.java.school.phones_shope.entity.Sale;
import com.piseth.java.school.phones_shope.entity.SaleDetail;

public interface saleDetailMapper {

	List<SaleDetail> toListSaleDetail(saleDTO dto, Sale sale, Map<Integer, Product> productMap);
}
