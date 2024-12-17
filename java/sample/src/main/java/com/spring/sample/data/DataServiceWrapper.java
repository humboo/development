package com.spring.sample.data;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class DataServiceWrapper implements IDataService{

	@Value("${dataServiceType}")
	private String dataServiceType;
	
	private IDataService dataService;
	
	@PostConstruct
	private void setDataService() {
		this.dataService = DataServiceFactory.getDataService(dataServiceType);
	}
	
	public DataServiceWrapper() {
	}

	@Override
	public Map<String, Object> getData(String key) {
		return this.dataService.getData(key);
	}
}
