package test.java;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import main.java.exc.model.Company;
import main.java.exc.persistence.SQLConnect;
import main.java.exc.service.ServiceDAOCompany;

public class SQLConnectTesting {

	String answer = "Connection established successfully!";

	
	@Test
	public void testConnection() {
		SQLConnect a = SQLConnect.getInstance();
		try {
			a.connect();
		} catch (SQLException e) {
			fail("SQL Exception while connecting to db with error type : " + e.getErrorCode());
		}
	}





}
