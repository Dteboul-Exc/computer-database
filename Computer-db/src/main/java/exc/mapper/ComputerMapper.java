package main.java.exc.mapper;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.exc.model.Company;
import main.java.exc.model.CompanyDTO;
import main.java.exc.model.Computer;
import main.java.exc.model.ComputerDTO;

public class ComputerMapper {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ComputerMapper.class);

    public static Optional<Computer> CDTOToComputer(final ComputerDTO computer) {
        Company company = Company.Builder.newInstance().setId(Integer.parseInt(computer.getCompany().getId())).setName(computer.getCompany().getName()).build();
        int id = Integer.parseInt(computer.getId());
        String name = computer.getName();
        Optional<LocalDate> introduced = DateMapper.StringConverterInput(computer.getIntroduced());
		Optional<LocalDate> discontinued = DateMapper.StringConverterInput(computer.getDiscontinued());
		 Computer result = Computer.Builder.newInstance().build();
		 result.setCompany(company);
		 result.setIntroduced(introduced.get());
		 result.setDiscontinued(discontinued.get());
		 result.setId(id);
		 result.setName(name);
		 return Optional.of(result);
    }


    
    public static Optional<ComputerDTO> computerToDTO(final Computer computer) throws ParseException {

        String name = computer.getName();
        String id = Integer.toString(computer.getId()) ;
        Optional<String> introduced  = DateMapper.DateConverter(computer.getIntroduced());
        Optional<String> discontinued  = DateMapper.DateConverter(computer.getDiscontinued());
        String company_id = Long.toString(computer.getCompany().getId());
        String company_name = computer.getCompany().getName();
        CompanyDTO company = CompanyDTO.Builder.newInstance().setId(company_id).setName(company_name).build();
		return Optional.of(ComputerDTO.Builder.newInstance().setName(name).setCompany(company).setId(id).setIntroduced(introduced.get()).setDiscontinued(discontinued.get()).build());

    }



}
