package com.piseth.java.school.phones_shope.service;

import java.util.List;

import com.piseth.java.school.phones_shope.entity.Model;

public interface ModelService {
	Model save (Model model);
	List<Model> findByBrandId(Integer id);
}
 