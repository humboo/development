package com.spring.sample.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import jakarta.annotation.PostConstruct;

@Profile("dev")
@Configuration
public class DevConfiguration {

	private static final Logger log = LoggerFactory.getLogger(DevConfiguration.class);

	@PostConstruct
	public void init() {
		log.info("dev only - DevConfiguration Post Construct");
		log.info("use case: configuration for dev environment only - setup db?");		
	}
}
