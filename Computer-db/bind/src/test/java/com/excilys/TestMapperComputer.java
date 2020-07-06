package com.excilys;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Optional;

import org.junit.Test;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
public class TestMapperComputer {
	@Test
	void testSuccessComputertoDTO() throws ParseException {
		Computer comp = new Computer();
		comp.setId(12);
		comp.setName("soldat");
		comp.setCompany(Company.Builder.newInstance().setId(15).setName("soldier of fortune").build());
		Optional<ComputerDTO> expect = ComputerMapper.computerToDTO(comp);
		assertEquals("soldat",expect.get().getName());
	}
}
