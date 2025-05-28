package com.piseth.java.school.phones_shope.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phones_shope.DTO.BrandDTO;
import com.piseth.java.school.phones_shope.DTO.ModelDTO;
import com.piseth.java.school.phones_shope.ExceptionHandle.ApiException;
import com.piseth.java.school.phones_shope.ExceptionHandle.ResourceNotFoundException;
import com.piseth.java.school.phones_shope.Mapper.BrandMapper;
import com.piseth.java.school.phones_shope.Mapper.ModelMapper;
import com.piseth.java.school.phones_shope.entity.Brand;
import com.piseth.java.school.phones_shope.service.BrandService;
import com.piseth.java.school.phones_shope.service.ModelService;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.56.1:4200"})
@RequestMapping("/brands")
public class BrandController {
	
	private final BrandService brandService;
	private final ModelService modelService;
	
	@PostMapping("/post")
	public ResponseEntity<?> create(@RequestBody BrandDTO dto) {
		return ResponseEntity.ok(brandService.save(BrandMapper.INSTANCE.toBrand(dto))); 
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
//	public ResponseEntity<?> getListOfBrandsUseStream(){
//		List<BrandDTO> lsBrandDto = brandService.getListOfBands().stream()
//			.map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
//			.collect(Collectors.toList());
//		return ResponseEntity.ok(lsBrandDto);
//	}
	
	@GetMapping("/filter")
		public ResponseEntity<?> getListByName(@PathParam("name") String name){
			return ResponseEntity.ok(BrandMapper.INSTANCE.toListOfBrandDto(brandService.getByName(name)));
		}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updatebyId(@PathVariable Integer id, @RequestBody BrandDTO dto) {
		Brand brand = brandService.updateByID(id, BrandMapper.INSTANCE.toBrand(dto));
		return ResponseEntity.ok(brandService.save(brand));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> updatebyId(@PathVariable Integer id){
		ResourceNotFoundException deleteByID = brandService.deleteByID(id);
		ApiException e = new ApiException(deleteByID.getMessage(), deleteByID.getStatus());
		return new ResponseEntity<>(e,deleteByID.getStatus());  
	}
	
	@GetMapping("{id}/brand")
	public ResponseEntity<?> findModelByBrandId(@PathVariable Integer id){
		List<ModelDTO> listDto = ModelMapper.INSTANCE.toListDto(modelService.findByBrandId(id));
		return ResponseEntity.ok(listDto);
	}
	
}
