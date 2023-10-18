package com.weber.utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.weber.exception.WeberLibraryException;

public class DatabaseConnector {
	
	public Connection getConnection(String callingClass) throws IOException, SQLException, WeberLibraryException{

		Properties props = new Properties();
		try(InputStream input = DatabaseConnector.class.getClassLoader().getResourceAsStream("db.properties")){
			props.load(input);
		}
		if ("com.weber.service.LibraryServiceImpl".equals(callingClass)) {
			String jdbcUrl = props.getProperty("jdbc.url");
			String userName = props.getProperty("jdbc.username");
			return DriverManager.getConnection(jdbcUrl, userName, "");
		} else if ("com.weber.tests.LibraryServiceValidTests".equals(callingClass)) {
			return DriverManager.getConnection("jdbc:h2:mem:test");
		} else {
			throw new WeberLibraryException("Unkown Calling Class: " + callingClass);
		}
	}

}
