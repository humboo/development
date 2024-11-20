package com.spring.sample.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//https://spring.io/guides/gs/scheduling-tasks/
@EnableScheduling
@Component
public class PeriodicScheduler {

	private final static Logger log = LoggerFactory.getLogger(PeriodicScheduler.class); 
	
	@Scheduled(fixedDelay= 10000)
	public void doScheduledTask() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		log.info("Ths time is now {}", dateFormat.format(new Date()));
	}
}
