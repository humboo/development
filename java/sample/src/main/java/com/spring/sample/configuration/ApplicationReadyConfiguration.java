package com.spring.sample.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class ApplicationReadyConfiguration {

	private final static Logger log = LoggerFactory.getLogger(ApplicationReadyConfiguration.class);
			
	@EventListener(ApplicationReadyEvent.class)
	public void ApplicationReadyEventAfterStartup() {
		String msg = "ApplicationReadyEvent after Start Up";
		System.out.println(msg);
		log.info(msg);
	}
}
