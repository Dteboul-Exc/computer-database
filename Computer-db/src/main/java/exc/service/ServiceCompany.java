package main.java.exc.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import main.java.exc.dto.CompanyDTO;
import main.java.exc.mapper.CompanyMapper;
import main.java.exc.model.Company;
import main.java.exc.persistence.DAOCompany;


@Service
public class ServiceCompany {
	
	@Autowired
	private DAOCompany DAOCompany;
	
	public ServiceCompany()
	{
	}
	
	public void set_DAOCompany(DAOCompany DAO)
	{
		this.DAOCompany = DAO;
	}
	public  Optional<List<CompanyDTO>> getAllCompany() {


	Logger logger = LoggerFactory.getLogger(DAOCompany.class);
    logger.debug("getAllCompany start");
    List<Company> dataset = DAOCompany.getAllCompany();
	List<CompanyDTO> result = new ArrayList<>();
	result.add(CompanyDTO.Builder.newInstance().setId("0").setName("none").build());
	for(Company company : dataset)
		result.add(CompanyMapper.companyToDTO(company));
	return Optional.of(result);
	}
	
	public Optional<CompanyDTO> getSpecificCompany(int id) 
	{

		Logger logger = LoggerFactory.getLogger(DAOCompany.class);
	    logger.debug("getA Company start");
	    CompanyDTO result;
	    if (id == 0) return Optional.empty();
	    try {
			result = CompanyMapper.companyToDTO(DAOCompany.getSpecificCompany(id).get());
			return Optional.of(result);
		} catch (ParseException e) {
			logger.error("error while getting all Computers : "+ e);
			return Optional.empty();
		}
	}
	
	public void setDAOCompany(final DAOCompany tDAOCompany)
	{
		this.DAOCompany = tDAOCompany;
	}
	public int deleteCompany(int id) {
		DAOCompany.deleteCompany(id);
		return 0;
	}

}
