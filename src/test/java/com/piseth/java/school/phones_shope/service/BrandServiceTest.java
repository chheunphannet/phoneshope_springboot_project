package com.piseth.java.school.phones_shope.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.piseth.java.school.phones_shope.ExceptionHandle.ResourceNotFoundException;
import com.piseth.java.school.phones_shope.entity.Brand;
import com.piseth.java.school.phones_shope.repository.BrandRepository;
import com.piseth.java.school.phones_shope.service.imp.BrandServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
	@Mock
	private BrandRepository brandRepository;

	private BrandService brandService;

	@BeforeEach
	public void setup() {
		brandService = new BrandServiceImpl(brandRepository);
	}

	@Test
	public void testBrandSave() {
//		//given
//		Brand brand = new Brand();
//		brand.setName("asus");
//		brand.setId(1);
//		//when
//		when(brandRepository.save(any(Brand.class))).thenReturn(brand);
//		Brand brandReturn = brandService.save(new Brand());
//		//then
//		assertEquals(1, brandReturn.getId());
//		assertEquals("asus", brandReturn.getName());

		// given
		Brand brand = new Brand();
		brand.setName("asus");
		brand.setBrand_id(1);
		// when
		brandService.save(brand);
		verify(brandRepository, times(1)).save(brand);
	}

	@Test
	public void testGetById() {
		// given
		Brand brand = new Brand();
		brand.setName("tuf");
		brand.setBrand_id(1);
		// when
		when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
		Brand brandReturn = brandService.getByID(1);
		// then
		assertEquals(1, brandReturn.getBrand_id());
		assertEquals("tuf", brandReturn.getName());

	}
	
	@Test
	public void testGetByIdThrowException() {
	    // when
	    when(brandRepository.findById(1)).thenReturn(Optional.empty());

	    // then
	    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
	        brandService.getByID(1);
	    });

	    assertEquals("not found this brand, id : 1", exception.getMessage());
	}

}
