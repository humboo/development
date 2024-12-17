package com.spring.sample.data;

public class DataServiceFactory {

	public static IDataService getDataService(String dataServiceType) {
		switch (dataServiceType) {
		case "mssql":
			return new MSSQLDataService();
		case "postgresql":
			return new PostgreSQLDataService();
		default:
			return new DummyDataService();
		}
	}
}
