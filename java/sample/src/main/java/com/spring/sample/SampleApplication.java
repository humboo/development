package com.spring.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.spring.*" })
@PropertySource("classpath:/static/profiles/application-${spring.profiles.active}.properties")
public class SampleApplication {

	private final static Logger log = LoggerFactory.getLogger(SampleApplication.class); 
	
	public static void main(String[] args) {
		log.info("Starting sample application");
		SpringApplication.run(SampleApplication.class, args);
	}

}