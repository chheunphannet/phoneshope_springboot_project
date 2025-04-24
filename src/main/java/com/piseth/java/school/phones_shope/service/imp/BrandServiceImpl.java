package com.piseth.java.school.phones_shope.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.entity.Brand;
import com.piseth.java.school.phones_shope.repository.BrandRepository;
import com.piseth.java.school.phones_shope.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Brand create(Brand brand) {
 		return brandRepository.save(brand);
	}

}
