package com.piseth.java.school.phones_shope.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phones_shope.ExceptionHandle.ResourceNotFoundException;
import com.piseth.java.school.phones_shope.entity.Color;
import com.piseth.java.school.phones_shope.repository.ColorRepository;
import com.piseth.java.school.phones_shope.service.ColorService;


@Service
public class ColorServiceImpl implements ColorService{
	@Autowired
	private ColorRepository colorRepository;

	@Override
	public Color getColorByID(Integer id) {
		return colorRepository.findById(id).orElseThrow(
				() ->  new ResourceNotFoundException("not found this brand, id : " + id, HttpStatus.NOT_FOUND));
	}

	@Override
	public Color createColor(Color color) {
		return colorRepository.save(color);
	}

}
