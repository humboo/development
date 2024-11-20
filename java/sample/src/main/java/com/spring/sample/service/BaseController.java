package com.spring.sample.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.sample.exception.InvalidInputException;

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
}
