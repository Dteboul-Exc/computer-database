package com.excilys;



import java.text.ParseException;
import java.util.Optional;

import org.junit.Test;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
class MapperComputer {

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
