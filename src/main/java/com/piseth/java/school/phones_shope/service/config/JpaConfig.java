package com.piseth.java.school.phones_shope.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
	
	@Bean
	AuditorAware<String> getCurrentAuditor() {
		return new AuditorAwareImpl();
	}
}
