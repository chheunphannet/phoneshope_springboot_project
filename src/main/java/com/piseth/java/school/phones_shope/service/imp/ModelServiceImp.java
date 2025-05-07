package com.piseth.java.school.phones_shope.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.ExceptionHandle.ResourceNotFoundException;
import com.piseth.java.school.phones_shope.entity.Model;
import com.piseth.java.school.phones_shope.repository.ModelRepository;
import com.piseth.java.school.phones_shope.service.ModelService;

@Service
public class ModelServiceImp implements ModelService {
	@Autowired
	private ModelRepository modelRepository;

	@Override
	public Model save(Model model) {
		return modelRepository.save(model);
	}

	@Override
	public List<Model> findByBrandId(Integer id) {
		return modelRepository.findByBrandBrandId(id);
	}

	@Override
	public Model getByID(Integer id) {
		return modelRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("not found this brand, id : " + id, HttpStatus.NOT_FOUND));
	}

} 
