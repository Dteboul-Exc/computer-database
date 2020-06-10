package test.java;

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

import main.java.exc.mapper.CompanyMapper;
import main.java.exc.model.Company;
import main.java.exc.model.CompanyDTO;
import main.java.exc.persistence.DAOCompany;
import main.java.exc.service.ServiceCompany;

public class Company_testing {

	private static  List<Company> list = new ArrayList<>();

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
		List<CompanyDTO> expected= new ArrayList<>();
		System.out.println("size is :" + list.size());
		for (Company element : list  )
		{
			expected.add(CompanyMapper.companyToDTO(element));
		}
		//Optional<List<CompanyDTO>> expected = Optional.of(list);
		Mockito.when(mockDAO.getAllCompany()).thenReturn(Optional.of(list));
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
