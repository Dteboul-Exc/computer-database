package com.excilys;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.excilys.dto.CompanyDTO;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.persistence.DAOCompany;
import com.excilys.service.ServiceCompany;

public class Company_testing {

	private static  List<Company> list = new ArrayList<Company>();

	private DAOCompany mockDAO = Mockito.mock(DAOCompany.class);
	private ServiceCompany service = new ServiceCompany();

	@BeforeClass
	public static void init() {
		list.add(new Company(12,"Comedy"));
		list.add(new Company(45,"Tragedy"));
		
	}
	
	@Before
	public void in() {
		MockitoAnnotations.initMocks(this);
		service.set_DAOCompany(mockDAO);
	}
	@Test
	public void testAllCompany() throws ClassNotFoundException, SQLException, ParseException {
		List<CompanyDTO> expected= new ArrayList<CompanyDTO>();
		System.out.println("size is :" + list.size());
		for (Company element : list  )
		{
			expected.add(CompanyMapper.companyToDTO(element));
		}
		//Optional<List<CompanyDTO>> expected = Optional.of(list);
		Mockito.when(mockDAO.getAllCompany()).thenReturn(list);
		List<CompanyDTO> obtained= this.service.getAllCompany().get();
		boolean check = expected.equals(obtained);
		assertEquals(true,check);

	}
	@Test
	public void testfindID() throws ClassNotFoundException, SQLException, ParseException {
		Company c = new Company(66,"Divine");
		Optional<Company> tested = Optional.of(c);
		Mockito.when(mockDAO.getSpecificCompany(66)).thenReturn(tested);
		boolean check = tested.get().equals(CompanyMapper.companyDTOToCompany(this.service.getSpecificCompany(66).get()).get());
		assertEquals(true,check);

	}

}
