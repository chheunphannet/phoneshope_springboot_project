package com.piseth.java.school.phones_shope.role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PermissionEnum {
	BRAND_WRITE("Brand:Write"), BRAND_READ("Brand:Read"), MODEL_READ("Model:Read"), MODEL_WRITE("Model:Write");
	
	private String decription;
}
