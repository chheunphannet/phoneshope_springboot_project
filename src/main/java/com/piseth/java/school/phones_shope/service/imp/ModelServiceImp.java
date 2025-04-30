package com.piseth.java.school.phones_shope.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.piseth.java.school.phones_shope.entity.Model;
import com.piseth.java.school.phones_shope.repository.ModelRepository;
import com.piseth.java.school.phones_shope.service.ModelService;


@Service
public class ModelServiceImp implements ModelService{
	@Autowired
	private ModelRepository modelRepository;

	@Override
	public Model save(Model model) {
		return modelRepository.save(model);
	}
	


}
