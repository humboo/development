package com.spring.sample.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentService {

	public enum ServiceType { Cash, Credit, Check }
	
	public void doPayment(ServiceType st) {
		
	}
}
