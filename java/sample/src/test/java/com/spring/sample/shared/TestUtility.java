package com.spring.sample.shared;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtility {
	
	public static <T> T getTestResources(String resourcePath, TypeReference<?> typeRef) {
		
		T resourceT = null;
		
		ClassPathResource clsPathRes = new ClassPathResource(resourcePath);
		if (clsPathRes.exists()) {
			String tData;
			try {
				tData = StreamUtils.copyToString(clsPathRes.getInputStream(), Charset.defaultCharset());
				ObjectMapper mapper = new ObjectMapper();
				resourceT = (T) mapper.readValue(tData, typeRef);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resourceT;
	}
	
	public static String getTestString(String resourcePath) {
		
		String resourceString = "";
		
		ClassPathResource clsPathRes = new ClassPathResource(resourcePath);
		if (clsPathRes.exists()) {
			String stringData = "";
			try {
				resourceString = StreamUtils.copyToString(clsPathRes.getInputStream(), Charset.defaultCharset());
				ObjectMapper mapper = new ObjectMapper();
				resourceString = mapper.readValue(stringData, String.class);
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return resourceString;
	}

}
