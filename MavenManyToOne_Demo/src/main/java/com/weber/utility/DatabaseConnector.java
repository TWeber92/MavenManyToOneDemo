package com.weber.utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.weber.exception.WeberLibraryException;

public class DatabaseConnector {

	public Connection getConnection(String callingClass) throws IOException, SQLException, WeberLibraryException {

		// 1. Test environment uses H2
		if ("com.weber.tests.LibraryServiceValidTests".equals(callingClass)) {
			return DriverManager.getConnection("jdbc:h2:mem:test");
		}

		// 2. Production environment (Render) uses environment variables
		else if ("com.weber.service.LibraryServiceImpl".equals(callingClass)) {
			String dbUrl = System.getenv("DATABASE_URL");

			if (dbUrl != null) {
				// Render is providing a Postgres URL
				String user = System.getenv("DATABASE_USERNAME");
				String pass = System.getenv("DATABASE_PASSWORD");
				// IMPORTANT: Add currentSchema=library so Postgres knows where to look
				String postgresUrl = dbUrl + "?currentSchema=library";

				return DriverManager.getConnection(postgresUrl, user, pass);
			}

			// 3. Local fallback (MySQL via db.properties)
			Properties props = new Properties();
			try (InputStream input = DatabaseConnector.class.getClassLoader().getResourceAsStream("db.properties")) {
				props.load(input);
			}
			String jdbcUrl = props.getProperty("jdbc.url");
			String userName = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password"); // Add this line
			return DriverManager.getConnection(jdbcUrl, userName, password);
		}

		else {
			throw new WeberLibraryException("Unknown Calling Class: " + callingClass);
		}
	}
}