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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyMapper;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.CrudRepository.QueryCompanyInterface;
import com.excilys.CrudRepository.QueryComputerInterface;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;


public class Mock_ServiceComputer_Testing {
	private static  List<Computer> list = new ArrayList<Computer>();
	@Mock
	private QueryComputerInterface mockDAO;
	
	@InjectMocks
	private ServiceComputer service;

	@BeforeClass
	public static void init() {
		list.add(Computer.Builder
				.newInstance().setId(12).setName("Jole").setCompany(new Company(14,"Kalo"))
				.build());
		list.add(Computer.Builder
				.newInstance().setId(54).setName("JolieFun").setCompany(new Company(14,"Kalo"))
				.build());
		list.add(Computer.Builder
				.newInstance().setId(54).setName("FunTime").setCompany(new Company(14,"Kalo"))
				.build());
		
	}
	
	@Before
	public void in() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testAllComputer() throws ClassNotFoundException, SQLException, ParseException {
		List<ComputerDTO> expected= new ArrayList<ComputerDTO>();
		for (Computer element : list  )
		{
			expected.add(ComputerMapper.computerToDTO(element).get());
		}
		Pageable page = PageRequest.of(0, 2);
		Mockito.when(mockDAO.findAll(page)).thenReturn(list);
		List<ComputerDTO> obtained= this.service.getAllComputer(0,2);
		assertEquals(obtained,expected);

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
		Mockito.when(mockDAO.findById((long) 45)).thenReturn(tested);
	boolean check = c.equals(ComputerMapper.CDTOToComputer((this.service.getSpecificComputer(45).get())).get());
		assertEquals(true,check);

	}
	@Test
	public void testAddComputerTesting() throws ClassNotFoundException, SQLException, ParseException {
		Computer c = Computer.Builder
				.newInstance().setId(45).setName("Jole")
				.setDiscontinued(LocalDate.of(2014, Month.JANUARY, 1))
				.setIntroduced(LocalDate.of(2013, Month.JANUARY, 1))
				.setCompany(new Company(14,"Kalo"))
				.build();
		Optional<Computer> tested = Optional.of(c);
		Mockito.when(mockDAO.save(c)).thenReturn(c);
		assertEquals(1,this.service.addComputer(ComputerMapper.computerToDTO(c).get()));

	}
	@Test
	public void testUpdateComputerTesting() throws ClassNotFoundException, SQLException, ParseException {
		Computer c = Computer.Builder
				.newInstance().setId(45).setName("Jole")
				.setDiscontinued(LocalDate.of(2014, Month.JANUARY, 1))
				.setIntroduced(LocalDate.of(2013, Month.JANUARY, 1))
				.setCompany(new Company(14,"Kalo"))
				.build();
		Computer c2 = Computer.Builder
				.newInstance().setId(22).setName("Jole")
				.setIntroduced(LocalDate.of(2013, Month.JANUARY, 1))
				.setCompany(new Company(14,"Kalo"))
				.build();
		Computer c3 = Computer.Builder
				.newInstance().setId(60).setName("Jole")
				.setCompany(new Company(14,"Kalo"))
				.build();
		Optional<Computer> tested = Optional.of(c);
		Mockito.when(mockDAO.save(c)).thenReturn(c);
		Mockito.when(mockDAO.save(c2)).thenReturn(c2);
		Mockito.when(mockDAO.save(c3)).thenReturn(c3);
		assertEquals(1,this.service.updateComputer(ComputerMapper.computerToDTO(c).get()));
		assertEquals(1,this.service.updateComputer(ComputerMapper.computerToDTO(c2).get()));
		assertEquals(1,this.service.updateComputer(ComputerMapper.computerToDTO(c3).get()));
	}
	
	@Test
	public void testGetCountComputer() 
	{
		Mockito.when(mockDAO.count()).thenReturn((long) 50);
		assertEquals(50,this.service.getCountComputer());
	}
	
	@Test
	public void testSearch_Computer()
	{
		List<ComputerDTO> expected= new ArrayList<ComputerDTO>();
		for (Computer element : list  )
		{
			expected.add(ComputerMapper.computerToDTO(element).get());
		}
		Mockito.when(mockDAO.findByNameContainingIgnoreCase("Jole")).thenReturn(list);
		List<ComputerDTO> obtained= this.service.Search_Computer("Jole");
		assertEquals(obtained,expected);
	}
	
	@Test
	public void testgetAllComputerOrderBy()
	{
		List<ComputerDTO> expected= new ArrayList<ComputerDTO>();
		for (Computer element : list  )
		{
			expected.add(ComputerMapper.computerToDTO(element).get());
		}
		Pageable page = PageRequest.of(0, 2);
		Mockito.when(mockDAO.findAll(page)).thenReturn(list);
		List<ComputerDTO> obtained= this.service.getAllComputerOrderBy("name", 0, 2);
		assertEquals(obtained,expected);
	}
	
}
