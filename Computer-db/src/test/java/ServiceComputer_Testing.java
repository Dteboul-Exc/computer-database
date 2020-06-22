package test.java;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import main.java.exc.dto.CompanyDTO;
import main.java.exc.dto.ComputerDTO;
import main.java.exc.mapper.CompanyMapper;
import main.java.exc.mapper.ComputerMapper;
import main.java.exc.model.Company;
import main.java.exc.model.Computer;
import main.java.exc.persistence.DAOCompany;
import main.java.exc.persistence.DAOComputer;
import main.java.exc.service.ServiceCompany;
import main.java.exc.service.ServiceComputer;

public class ServiceComputer_Testing {
	private static  List<Computer> list = new ArrayList<>();

	private DAOComputer mockDAO = Mockito.mock(DAOComputer.class);
	private ServiceComputer service = new ServiceComputer();

	@BeforeClass
	public static void init() {
		list.add(Computer.Builder
				.newInstance().setId(12).setName("Jole").setCompany(new Company(14,"Kalo"))
				.build());
		list.add(Computer.Builder
				.newInstance().setId(54).setName("Jole").setCompany(new Company(14,"Kalo"))
				.build());
		
	}
	
	@Before
	public void in() {
		MockitoAnnotations.initMocks(this);
		service.set_ComputerValidator(mockDAO);
	}
	@Test
	public void testAllComputer() throws ClassNotFoundException, SQLException, ParseException {
		List<ComputerDTO> expected= new ArrayList<>();
		System.out.println("size is :" + list.size());
		for (Computer element : list  )
		{
			expected.add(ComputerMapper.computerToDTO(element).get());
		}
		//Optional<List<CompanyDTO>> expected = Optional.of(list);
		Mockito.when(mockDAO.getAllComputer(0,1)).thenReturn(list);
		List<ComputerDTO> obtained= this.service.getAllComputer(0,1);
		boolean check = expected.equals(obtained);
		assertEquals(true,check);

	}
	@Test
	public void testfindID() throws ClassNotFoundException, SQLException, ParseException {
		Computer c = Computer.Builder
				.newInstance().setId(45).setName("Jole")
				.setDiscontinued(LocalDate.of(2014, Month.JANUARY, 1))
				.setIntroduced(LocalDate.of(2013, Month.JANUARY, 1))
				.setCompany(new Company(14,"Kalo"))
				.build();
		Optional<Computer> tested = Optional.of(c);
		//Mockito.when(mockDAO.getSpecificComputer(0)).thenReturn(tested);
		Mockito.when(mockDAO.get_Computer_By_Id(45)).thenReturn(tested);
	boolean check = c.equals(ComputerMapper.CDTOToComputer((this.service.getSpecificComputer(45).get())).get());
		assertEquals(true,check);

	}
	@Test
	public void DeleteComputerTesting() throws ClassNotFoundException, SQLException, ParseException {
		Computer c = Computer.Builder
				.newInstance().setId(45).setName("Jole")
				.setDiscontinued(LocalDate.of(2014, Month.JANUARY, 1))
				.setIntroduced(LocalDate.of(2013, Month.JANUARY, 1))
				.setCompany(new Company(14,"Kalo"))
				.build();
		Optional<Computer> tested = Optional.of(c);
		//Mockito.when(mockDAO.getSpecificComputer(0)).thenReturn(tested);
		Mockito.when(mockDAO.deleteSpecificComputer(45)).thenReturn(1);
		assertEquals(1,this.service.deleteSpecificComputer(45));

	}
	@Test
	public void AddComputerTesting() throws ClassNotFoundException, SQLException, ParseException {
		Computer c = Computer.Builder
				.newInstance().setId(45).setName("Jole")
				.setDiscontinued(LocalDate.of(2014, Month.JANUARY, 1))
				.setIntroduced(LocalDate.of(2013, Month.JANUARY, 1))
				.setCompany(new Company(14,"Kalo"))
				.build();
		Optional<Computer> tested = Optional.of(c);
		//Mockito.when(mockDAO.getSpecificComputer(0)).thenReturn(tested);
		Mockito.when(mockDAO.deleteSpecificComputer(45)).thenReturn(1);
		assertEquals(1,this.service.deleteSpecificComputer(45));

	}
	/*@Test//
	public void UpdateComputerTesting() throws ClassNotFoundException, SQLException, ParseException {
		Computer c = Computer.Builder
				.newInstance().setId(45).setName("Jole")
				.setDiscontinued(LocalDate.of(2014, Month.JANUARY, 1))
				.setIntroduced(LocalDate.of(2013, Month.JANUARY, 1))
				.setCompany(new Company(14,"Kalo"))
				.build();
		Optional<Computer> tested = Optional.of(c);
		//Mockito.when(mockDAO.getSpecificComputer(0)).thenReturn(tested);
		Mockito.when(mockDAO.updateComputer("Jole", LocalDate.of(2013, Month.JANUARY, 1), LocalDate.of(2014, Month.JANUARY, 1), (long)14, 45)).thenReturn(1);
		assertEquals(1,this.service.deleteSpecificComputer(45));

	}*/
}


