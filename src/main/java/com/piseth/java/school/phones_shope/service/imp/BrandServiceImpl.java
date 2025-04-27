package com.piseth.java.school.phones_shope.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.ExceptionHandle.ResourceNotFoundException;
import com.piseth.java.school.phones_shope.entity.Brand;
import com.piseth.java.school.phones_shope.repository.BrandRepository;
import com.piseth.java.school.phones_shope.service.BrandService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
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

	@Override
	public ResourceNotFoundException deleteByID(Integer id) {
		if(brandRepository.findById(id).isPresent()) {
			brandRepository.deleteById(id);
			return  new ResourceNotFoundException("this brand deleted! , id : " + id,HttpStatus.OK);
		}
		return new ResourceNotFoundException("not found brand , id : " + id,HttpStatus.NOT_FOUND);
	}

	@Override
	public List<Brand> getListOfBands() {
		return brandRepository.findAll();
	}

	@Override
	public List<Brand> getByName(String name) {
		return brandRepository.findByNameContainingIgnoreCase(name);
	}


	


}
