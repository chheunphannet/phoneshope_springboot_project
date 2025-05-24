package com.piseth.java.school.phones_shope.projection;

import java.math.BigDecimal;

public interface ProductSold {
	Integer getProductId();
	String getProductName();
	Integer getUnit();
	BigDecimal getTotalAmount();
}
