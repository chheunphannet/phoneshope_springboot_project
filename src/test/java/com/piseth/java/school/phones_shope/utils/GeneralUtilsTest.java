package com.piseth.java.school.phones_shope.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.piseth.java.school.phones_shope.service.utils.GeneralUtils;

public class GeneralUtilsTest {
	@Test
	public void testListOfOddList() {
		// given
		List<Integer> ls = List.of(1, 3, 2, 1, 4, 5, 6, 4, 7);
		// when
		List<Integer> oddLs = GeneralUtils.listOfOdd(ls);
		// then
		assertEquals(5, oddLs.size());
		assertEquals(1, oddLs.get(0));
		assertEquals(3, oddLs.get(1));
		assertEquals(1, oddLs.get(2));
		assertEquals(5, oddLs.get(3));
		assertEquals(7, oddLs.get(4));

	}
}
