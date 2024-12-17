package com.spring.sample.service;

import java.io.File;
import java.util.Map;

import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.spring.sample.data.IDataService;
import com.spring.sample.shared.TestUtility;

public class BaseTest {

	protected static String someconfig;
	
	private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

	public static void init(BaseController bc) {
		
		Map<String, Object> configMap = getTestMap("data" + File.separator + "configuration.json");
		if (bc != null) {
			log.info("BaseTest - controller init");
		}
		log.info("Test configuration.json: " + configMap.toString());
	}
	
	public static Map<String, Object> getTestMap(String key) {
		
		return TestUtility.getTestResources(key, new TypeReference<Map<String, Object>>(){});
	}
}
