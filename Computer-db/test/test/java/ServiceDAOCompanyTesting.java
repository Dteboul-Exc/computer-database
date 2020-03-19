package test.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import main.java.exc.persistence.DAOCompany;
import main.java.exc.model.Company;
import main.java.exc.persistence.SQLConnect;
import main.java.exc.service.ServiceDAOCompany;



class ServiceDAOCompanyTesting {
	private DAOCompany  mockDAOCompany = Mockito.mock(DAOCompany.class);
	private ServiceDAOCompany ServiceDAO = new ServiceDAOCompany();
	
	@Before
	public void testInitialization()
	{
		MockitoAnnotations.initMocks(this);
		ServiceDAO.set
		
	}
	
	@Test
	public void testGetAllCompany() throws SQLException, ParseException{
		Optional<Company> TestCompany =  Optional.of(Company.Builder.newInstance().setId(10).setName("aleola").build());
		Mockito.when(ServiceDAO.getSpecificCompany(10)).thenReturn(TestCompany);
		Mockito.when(ServiceDAO.getSpecificCompany(0)).thenReturn(Optional.empty());
		Assert.assertEquals(Optional.empty(), this.mockDAOCompany.getSpecificCompany(0));
		Assert.assertEquals(TestCompany, this.mockDAOCompany.getSpecificCompany(10));
		
	}
}
