package com.spring.sample.shared;

import java.nio.charset.StandardCharsets;

import org.springframework.scheduling.annotation.Async;

public class Utility {

	private static int count = 0;
	private static int syncCount = 0;
	
	// https://www.baeldung.com/java-string-encode-utf-8
	public static String sanitizedString(String input) {
		return new String(input.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
	}
	
	public static int getNextInt() throws InterruptedException {
		
		return count++;
	}
	
	public synchronized static int getNextIntSync() throws InterruptedException {

		return syncCount++;
	}
}
