package com.spring.sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.sample.shared.Utility;

@EnableScheduling
@Component
public class PeriodicSchedulerTestSynchronized {

	private final static Logger log = LoggerFactory.getLogger(PeriodicSchedulerTestSynchronized.class); 
	
	@Scheduled(fixedDelay= 10000)
	public void getInt1() throws InterruptedException {
		
		Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                try {
                	Utility.getNextInt();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                try {
					Utility.getNextInt();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		
		log.info(" The count is {}", Utility.getNextInt());
		
		
		Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                try {
                	Utility.getNextIntSync();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                try {
					Utility.getNextIntSync();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		thread3.start();
		thread4.start();
		thread3.join();
		thread4.join();
		log.info(" The sync count is {}", Utility.getNextIntSync());
	}
}
