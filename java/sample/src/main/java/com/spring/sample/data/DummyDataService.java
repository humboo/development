package com.spring.sample.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

public class DummyDataService implements IDataService {

	private String dataServiceType = "";
	
	public DummyDataService() {
		
	}
	
	@Override
	public Map<String, Object> getData(String key) {
		Map<String, Object> map = new HashMap<>();
		map.put("lastName", "Jackson");
		return map;
	}
	
	public Map<String, Object> getMapData() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("lastName", "Jackson");
		
		return map;
	}
	
	// generic get data function
	
	public Map<String, Object> getClass(String cid) {
		
		Map<String, Object> map = new HashMap<>();
		// query from database?
		map.put("classId", cid);
		map.put("className", "Hardcoded Demo");
		
		return map;
	}

	
}
