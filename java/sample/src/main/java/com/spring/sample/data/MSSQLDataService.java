package com.spring.sample.data;

import java.util.HashMap;
import java.util.Map;

public class MSSQLDataService implements IDataService {

	public MSSQLDataService() {
		
	}
	
	@Override
	public Map<String, Object> getData(String key) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("data", "SQL");
		return map;
	}

}
