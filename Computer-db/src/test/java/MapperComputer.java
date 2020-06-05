package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import main.java.exc.mapper.ComputerMapper;
import main.java.exc.model.Company;
import main.java.exc.model.Computer;
import main.java.exc.model.ComputerDTO;

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
