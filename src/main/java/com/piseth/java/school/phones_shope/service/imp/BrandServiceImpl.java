package com.piseth.java.school.phones_shope.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.DTO.BrandDTO;
import com.piseth.java.school.phones_shope.ExceptionHandle.ResourceNotFoundException;
import com.piseth.java.school.phones_shope.entity.Brand;
import com.piseth.java.school.phones_shope.repository.BrandRepository;
import com.piseth.java.school.phones_shope.service.BrandService;
import com.piseth.java.school.phones_shope.util.Mapper;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Brand save(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public Brand getByID(Integer id) {
		return brandRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("not found this brand, id : " + id,HttpStatus.NOT_FOUND));
	}

	@Override
	public Brand updateByID(Integer id, Brand brandUpdate) {
		 Brand brand = getByID(id);
		 brand.setName(brandUpdate.getName());
		return brand;
	}

}
