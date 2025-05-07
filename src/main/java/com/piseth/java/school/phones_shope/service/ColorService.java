package com.piseth.java.school.phones_shope.service;

import com.piseth.java.school.phones_shope.entity.Color;

public interface ColorService {
	Color getColorByID(Integer id);
	Color createColor(Color color);
}
