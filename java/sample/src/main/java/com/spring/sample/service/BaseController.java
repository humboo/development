package com.spring.sample.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.sample.exception.InvalidInputException;

import jakarta.servlet.http.HttpServletRequest;

public class BaseController {

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);

	protected JSONObject getJsonStringObject(String jsonString) {
		if (jsonString.isEmpty())
			jsonString = "{}";
		try  {
			return new JSONObject(jsonString);
		}
		catch(JSONException ex) {
			throw new InvalidInputException("Invalid Json String");
		}
	}
	
	protected String getRequestAttribute(String attributeName) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		return request.getAttribute(attributeName).toString();
	}
	
	
}
