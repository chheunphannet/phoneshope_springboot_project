package com.piseth.java.school.phones_shope.role;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import static com.piseth.java.school.phones_shope.role.PermissionEnum.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
	ADMIN(Set.of(BRAND_READ, BRAND_WRITE, MODEL_READ, MODEL_WRITE)), SALE(Set.of(BRAND_READ, MODEL_READ));

	private Set<PermissionEnum> permission;
	
	public Set<SimpleGrantedAuthority> getAuthority(){
		Set<SimpleGrantedAuthority> grantedAuthority = this.permission
				.stream()
				.map(p -> new SimpleGrantedAuthority(p.getDecription()))
				.collect(Collectors.toSet());
		
		System.out.println(grantedAuthority);
		return grantedAuthority;
	}
}
