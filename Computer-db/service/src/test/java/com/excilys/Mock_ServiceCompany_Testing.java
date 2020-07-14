package com.excilys;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.DAO.QueryCompanyInterface;
import com.excilys.DAO.QueryComputerInterface;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyMapper;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;


public class Mock_ServiceCompany_Testing {
	private static  List<Company> list = new ArrayList<Company>();

	@Mock
	private QueryCompanyInterface mockDAO;
	@InjectMocks
	private ServiceCompany service;

	@Autowired
	CompanyMapper CompanyMapper;
	@BeforeClass
	public static void init() {
		list.add(Company.Builder.newInstance().setId(12).setName("Bob le bricoleur").build());
		list.add(Company.Builder.newInstance().setId(25).setName("Bob le tricheur").build());
		
	}
	
	@Before
	public void in() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testAllCompany() throws ClassNotFoundException, SQLException, ParseException {
		List<CompanyDTO> expected= new ArrayList<CompanyDTO>();
		for (Company element : list  )
		{
			expected.add(CompanyMapper.companyToDTO(element).get());
		}
		//Optional<List<CompanyDTO>> expected = Optional.of(list);
		Mockito.when(mockDAO.findAll()).thenReturn(list);
		List<CompanyDTO> obtained= this.service.getAllCompany();
		boolean check = expected.equals(obtained);
		assertEquals(true,check);

	}
	
	@Test
	public void testgetSpecificCompany() throws ClassNotFoundException, SQLException, ParseException {
		Optional<Company> expected= Optional.of(Company.Builder.newInstance().setId(12).setName("Bob le bricoleur").build());
		Optional<Company> expected2= Optional.of(Company.Builder.newInstance().setId(20).setName("Love").build());
		Mockito.when(mockDAO.findById((long) 12)).thenReturn(expected);
		Mockito.when(mockDAO.findById((long) 20)).thenReturn(expected2);
		Optional<CompanyDTO> obtained= this.service.getSpecificCompany(12);
		assertEquals(obtained.get(),CompanyMapper.companyToDTO(expected.get()));
		obtained= this.service.getSpecificCompany(20);
		assertEquals(obtained.get(),CompanyMapper.companyToDTO(expected2.get()));
	}
	
	
}
