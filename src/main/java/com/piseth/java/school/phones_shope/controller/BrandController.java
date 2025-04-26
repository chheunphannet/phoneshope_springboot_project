package com.piseth.java.school.phones_shope.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phones_shope.DTO.BrandDTO;
import com.piseth.java.school.phones_shope.ExceptionHandle.ApiException;
import com.piseth.java.school.phones_shope.ExceptionHandle.ResourceNotFoundException;
import com.piseth.java.school.phones_shope.Mapper.BrandMapper;
import com.piseth.java.school.phones_shope.entity.Brand;
import com.piseth.java.school.phones_shope.service.BrandService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/brands")
public class BrandController {
	@Autowired
	private BrandService brandService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Brand brand) {
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brandService.save(brand))); 
	} 
	
	@GetMapping("{id}")
	public ResponseEntity<?> getBrand(@PathVariable Integer id) {
		Brand brand = brandService.getByID(id);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand)); 
	}
	
	@GetMapping
	public ResponseEntity<?> getListOfBrands(){
		return ResponseEntity.ok(BrandMapper.INSTANCE.toListOfBrandDto(brandService.getListOfBands()));
	}
	
	//@GetMapping @covert to list of brandDto use stream
	public ResponseEntity<?> getListOfBrandsUseStream(){
		List<BrandDTO> lsBrandDto = brandService.getListOfBands().stream()
			.map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
			.collect(Collectors.toList());
		return ResponseEntity.ok(lsBrandDto);
	}
	
	@GetMapping("/filter")
		public ResponseEntity<?> getListByName(@PathParam("name") String name){
			return ResponseEntity.ok(BrandMapper.INSTANCE.toListOfBrandDto(brandService.getByName(name)));
		}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updatebyId(@PathVariable Integer id, @RequestBody Brand brandUpdate) {
		Brand brand = brandService.updateByID(id, brandUpdate);
		return ResponseEntity.ok(brandService.save(brand));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> updatebyId(@PathVariable Integer id){
		ResourceNotFoundException deleteByID = brandService.deleteByID(id);
		ApiException e = new ApiException(deleteByID.getMessage(), deleteByID.getStatus());
		return new ResponseEntity<>(e,deleteByID.getStatus()); 
	}
}
