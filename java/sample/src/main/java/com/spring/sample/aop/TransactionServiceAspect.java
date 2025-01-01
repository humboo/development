package com.spring.sample.aop;

import java.util.Map;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.spring.sample.service.PaymentService.ServiceType;

@Aspect
@Component
public class TransactionServiceAspect {

	//@Pointcut("execution(public void com.spring.sample.service.BasicController.doPayment())")
	@Pointcut("execution(public void com.spring.sample.service.PaymentService.doPayment(..))")
	public void p1() {
		
	}
	
	@Before("p1()")
	public void beginTransaction() {
		System.out.println("Transaction Begins!");
	}
	
	@After("p1()")
	public void completeTransaction() {
		System.out.println("Transaction Completes!");
	}
	
	@AfterReturning("p1()")
	public void commitTransaction() {
		System.out.println("Transaction Committed!");
	}
	
	@AfterThrowing("p1()")
	public void rollbackTransaction() {
		System.out.println("Transaction Rolled Back!");
	}
	
}
