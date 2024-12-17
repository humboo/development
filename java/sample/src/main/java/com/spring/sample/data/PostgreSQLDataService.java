package com.spring.sample.data;

import java.util.HashMap;
import java.util.Map;

public class PostgreSQLDataService implements IDataService {

	public PostgreSQLDataService() {
		
	}
	
	@Override
	public Map<String, Object> getData(String key) {
		Map<String, Object> map = new HashMap<>();
		map.put("server", "postgreSQL");
		return map;
	}

}
