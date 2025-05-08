package com.piseth.java.school.phones_shope.service.utils;

import java.util.List;

public class GeneralUtils {
	public static List<Integer> listOfOdd(List<Integer> list) {
		return list.stream()
				.filter(n -> n % 2 != 0)
				.toList();
	}
}
