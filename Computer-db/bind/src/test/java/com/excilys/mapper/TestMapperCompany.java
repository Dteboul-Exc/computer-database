package com.excilys.mapper;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.configuration.SpringConfiguration;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class TestMapperCompany {
	
	
	@Autowired
	CompanyMapper CompanyMapper;
	@Test
	public void testSuccessCompanytoDTO() throws ParseException {
		Company comp1 = Company.Builder.newInstance().setId(12).setName("Travolta").build();
		Company comp2 = Company.Builder.newInstance().setId(16).setName("Travoa").build();
		Optional<CompanyDTO> expect = CompanyMapper.companyToDTO(comp1);
		assertEquals(comp1,CompanyMapper.companyDTOToCompany(expect.get()).get());
		expect = CompanyMapper.companyToDTO(comp2);
		assertEquals(comp2,CompanyMapper.companyDTOToCompany(expect.get()).get());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testnullCompanytoDTO() throws ParseException {
		CompanyMapper.companyToDTO(null);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testnullDTOToCompany() throws ParseException {
		CompanyMapper.companyDTOToCompany(null);
	}


}
