package com.excilys.mapper;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;

/**
 * @author dteboul Mapper that transform a Computer Object into a ComputerDTO
 *         object or vice versa
 */
public class ComputerMapper {
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ComputerMapper.class);

	/**
	 * Mapper that transform a ComputerDTO Object into a Computer
	 * 
	 * @param computer
	 * @return
	 */
	public static Optional<Computer> CDTOToComputer(final ComputerDTO computer) {
		Company company = Company.Builder.newInstance().setId(Integer.parseInt(computer.getCompany().getId()))
				.setName(computer.getCompany().getName()).build();
		int id = Integer.parseInt(computer.getId());
		String name = computer.getName();
		Optional<LocalDate> introduced = Optional.ofNullable(null);
		if ((computer.getIntroduced() != null) && (!computer.getIntroduced().isBlank())
				&& (!computer.getIntroduced().equals("NULL")))
			introduced = DateMapper.StringConverterInput(computer.getIntroduced());
		Optional<LocalDate> discontinued = Optional.ofNullable(null);
		if ((computer.getDiscontinued() != null) && (!computer.getDiscontinued().isBlank())
				&& (!computer.getDiscontinued().equals("NULL")))
			discontinued = DateMapper.StringConverterInput(computer.getDiscontinued());
		Computer result = Computer.Builder.newInstance().setCompany(company).setIntroduced(introduced.orElse(null))
				.setDiscontinued(discontinued.orElse(null)).setId(id).setName(name).build();
		return Optional.of(result);
	}

	/**
	 * Mapper that transform a Computer Object into a ComputerDTO
	 * 
	 * @param computer
	 * @return
	 * @throws ParseException
	 */
	public static Optional<ComputerDTO> computerToDTO(final Computer computer) {

		String name = computer.getName();
		String id = Long.toString(computer.getId());
		Optional<String> introduced;
		try {
			introduced = DateMapper.DateConverter(computer.getIntroduced());
			Optional<String> discontinued = DateMapper.DateConverter(computer.getDiscontinued());
			CompanyDTO company;
			if (!(computer.getCompany() == null)) {
				company = CompanyMapper.companyToDTO(computer.getCompany());
			} else {
				company = CompanyDTO.Builder.newInstance().setId("0").build();
			}
			return Optional.of(ComputerDTO.Builder.newInstance().setName(name).setCompany(company).setId(id)
					.setIntroduced(introduced.orElse(null)).setDiscontinued(discontinued.orElse(null)).build());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.ofNullable(null);

	}

}
