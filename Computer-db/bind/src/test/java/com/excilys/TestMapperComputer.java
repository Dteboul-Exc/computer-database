package com.excilys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;

import com.excilys.configuration.SpringConfiguration;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class TestMapperComputer {
	
	@Autowired
	ComputerMapper ComputerMapper;
	@Test
	public void testSuccessComputertoDTO() throws ParseException {
		Computer comp = new Computer();
		comp.setId(12);
		comp.setName("soldat");
		comp.setIntroduced(LocalDate.parse("1990-10-12"));
		comp.setDiscontinued(LocalDate.parse("1990-12-12"));
		comp.setCompany(Company.Builder.newInstance().setId(15).setName("soldier of fortune").build());
		Optional<ComputerDTO> expect = ComputerMapper.computerToDTO(comp);
		assertEquals(comp,ComputerMapper.CDTOToComputer(expect.get()).get());
	}
	
	@Test
	public void testSuccessNoDatesComputertoDTO() throws ParseException {
		Computer comp = new Computer();
		comp.setId(12);
		comp.setName("soldat");
		comp.setCompany(Company.Builder.newInstance().setId(15).setName("soldier of fortune").build());
		Optional<ComputerDTO> expect = ComputerMapper.computerToDTO(comp);
		assertEquals(comp,ComputerMapper.CDTOToComputer(expect.get()).get());
	}
	
	@Test
	public void testNullComputertoDTO() throws ParseException {
		Computer comp = new Computer();
		comp.setId(12);
		comp.setName("soldat");
		comp.setCompany(Company.Builder.newInstance().setId(15).setName("soldier of fortune").build());
		Optional<ComputerDTO> expect = ComputerMapper.computerToDTO(null);
		assertEquals(comp,ComputerMapper.CDTOToComputer(expect.get()).get());
	}
	
	@Test
	public void testOddCharacterNoDatesComputertoDTO() throws ParseException {
		Computer comp = new Computer();
		comp.setId(12);
		comp.setName("soldat");
		comp.setCompany(Company.Builder.newInstance().setId(15).setName("soldier of fortune").build());
		Optional<ComputerDTO> expect = ComputerMapper.computerToDTO(comp);
		assertEquals(comp,ComputerMapper.CDTOToComputer(expect.get()).get());
	}
}
