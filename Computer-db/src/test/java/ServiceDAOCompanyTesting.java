package test.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import main.java.exc.model.Company;
import main.java.exc.persistence.SQLConnect;
import main.java.exc.service.ServiceDAOCompany;

class ServiceDAOCompanyTesting {

	@Test
	public void testGetAllCompany() {
		SQLConnect a = SQLConnect.getInstance();
		try {
			a.connect();
			Optional<List<Company>> test_company = ServiceDAOCompany.getAllCompany();
			assertNotNull(test_company);
		} catch (SQLException e) {
			fail("SQL Exception while connecting to db with with error type : " + e.getErrorCode());
		}
	}
}
