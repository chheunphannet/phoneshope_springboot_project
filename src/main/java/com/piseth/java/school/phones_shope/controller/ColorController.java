package com.piseth.java.school.phones_shope.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phones_shope.DTO.ColorDTO;
import com.piseth.java.school.phones_shope.Mapper.ColorMapper;
import com.piseth.java.school.phones_shope.entity.Color;
import com.piseth.java.school.phones_shope.service.ColorService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/color")
public class ColorController {
	private final ColorService colorService;
	
	@PostMapping()
	public ResponseEntity<?> createColor(@RequestBody ColorDTO dto){
		Color color = ColorMapper.INSTANCE.toColor(dto);
		return ResponseEntity.ok(colorService.createColor(color));
	}
}
