package com.spring.sample.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.util.Assert;

import com.spring.sample.data.IDataService;

public class BasicControllerTest extends BaseTest {
	
	private static final Logger log = LoggerFactory.getLogger(BasicControllerTest.class);

	private static BasicController bc = new BasicController();
	private static IDataService mockServiceData = Mockito.mock(IDataService.class);	
		
	
	@BeforeAll 
	public static void beforeAll() {
		log.info("@BeforeAll ...");
		init(bc);
		bc.setDataService(mockServiceData);   // move to base
	}
	
	
	@BeforeEach
	public void beforeEach() {
		log.info("@BeforeEach ...");
	}
	
	@AfterEach
	public void afterEach() {
		log.info("@AfterEach ...");
	}
	
	
	@BeforeTestClass
	public static void setupBeforeTestClass() {
		log.info("@BeforeTestClass ...");
	}
	
	@Test
	void TestGetDataFromService() {
		Map<String, Object> mockData = new HashMap<>();
		mockData.put("mock", "mockData");
		when(mockServiceData.getData(Mockito.anyString())).thenReturn(mockData);
		
		ResponseEntity<Map<String, Object>> data = bc.getDataFromService("cid2000");
		Map<String, Object> result = data.getBody();
		
		Assert.isTrue(result.containsKey("mock"), "No mock key");
		
	}
	
	@Test
	void TestCtoF() {
		ResponseEntity<Map<String, Object>> data = bc.getCelciusToFahrenheit(20);
		Map<String, Object> result = data.getBody();
		String f = result.get("farenheit").toString();
		assertTrue(f.equals("68"), "Farenheit should be 68 for 20 degree celcious!");
	}
	
	@Test
	void testStaticData() {
		ResponseEntity<Map<String, Object>> data = bc.getStaticData();
		
		Map<String, Object> mapBody = data.getBody();
		Assert.isTrue(mapBody.containsKey("id"), "No id field");
	}

}
