package com.piseth.java.school.phones_shope.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.piseth.java.school.phones_shope.entity.Brand;
@DataJpaTest
public class BrandRepositoryTest {
	@Autowired
	private BrandRepository brandRepository;
	@Test
	public void testBrandRepository() {
		Brand b1, b2, b3;
		b1 = new Brand();
		b1.setName("apple");
		b2 = new Brand();
		b2.setName("asus");
		b3 = new Brand();
		b3.setName("vivo");
		
		brandRepository.save(b1);
		brandRepository.save(b2);
		brandRepository.save(b3);
		
		List<Brand> byNameContainingIgnoreCase = brandRepository.findByNameContainingIgnoreCase("a");
		// then
		assertEquals(2, byNameContainingIgnoreCase.size());
		assertEquals("apple", byNameContainingIgnoreCase.get(0).getName());
		assertEquals("asus", byNameContainingIgnoreCase.get(1).getName());
		
	}
}
