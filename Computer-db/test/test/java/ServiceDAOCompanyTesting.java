package test.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import main.java.exc.persistence.DAOCompany;
import main.java.exc.model.Company;
import main.java.exc.persistence.SQLConnect;
import main.java.exc.service.ServiceDAOCompany;
import static org.mockito.Mockito.*;


class ServiceDAOCompanyTesting {
	private DAOCompany  mockDAOCompany = Mockito.mock(DAOCompany.class);
	private ServiceDAOCompany serviceDAO = new ServiceDAOCompany();
	

	
	String companyId = "16";
	
	Optional<Company> mCompany = Optional.of( Company.Builder.newInstance().setId(16).setName("Tester_company").build());
	List<Company> companyList = new ArrayList<>();
	
	
	
	@Before
	public void testInitialization() throws SQLException
	{
		MockitoAnnotations.initMocks(this);
		serviceDAO.setDAOCompany(mockDAOCompany);

		
	}
	
	
	@Test 
	public void findByIdTest() throws SQLException, ParseException{
		Company company = Company.Builder.newInstance().setId(55).setName("Comedy").build();
		Optional<Company> tested = Optional.of(company);

		Mockito.when(mockDAOCompany.getSpecificCompany(55)).thenReturn(tested);
		Mockito.when(mockDAOCompany.getSpecificCompany(0)).thenReturn(Optional.empty());
		Optional<Company> tested2 = this.serviceDAO.getSpecificCompany(55);
		System.out.println(tested2.get().getName());
		Assert.assertEquals(Optional.empty(), this.serviceDAO.getSpecificCompany(0));
		Assert.assertEquals(tested, this.serviceDAO.getSpecificCompany(55));		
	}
	/*
	@Test
	public void testGetAllCompany() throws SQLException, ParseException{



		
	}*/
}
