package com.piseth.java.school.phones_shope.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phones_shope.DTO.ModelDTO;
import com.piseth.java.school.phones_shope.Mapper.ModelMapper;
import com.piseth.java.school.phones_shope.entity.Model;
import com.piseth.java.school.phones_shope.service.ModelService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/model")
public class ModelController {
	private final ModelService modelService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<?> createModel(@RequestBody ModelDTO modelDTO) {
		Model model = modelMapper.toModel(modelDTO);
		return ResponseEntity.ok(modelService.save(model));
	}
}
