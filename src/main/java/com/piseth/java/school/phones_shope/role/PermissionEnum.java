package com.piseth.java.school.phones_shope.role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PermissionEnum {
	BRAND_WRITE("brand:write"),
	BRAND_READ("brand:read"),
	MODEL_READ("model:read"),
	MODEL_WRITE("model:write"),
	;
	
	private String decription;
}
