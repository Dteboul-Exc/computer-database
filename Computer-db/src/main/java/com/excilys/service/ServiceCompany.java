package com.excilys.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dto.CompanyDTO;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.persistence.DAOCompany;
import com.excilys.persistence.QueryCompanyInterface;


/**
 * @author dteboul
 *Assure the link between the servlet and the DAO
 */
@Transactional
@Service
public class ServiceCompany {
	
	@Autowired
	private DAOCompany DAOCompany;
	
	@Autowired
	private  QueryCompanyInterface repo;
	
	public void set_DAOCompany(DAOCompany DAO)
	{
		this.DAOCompany = DAO;
	}
	
	
	public  List<CompanyDTO> getAllCompany() {
	Logger logger = LoggerFactory.getLogger(DAOCompany.class);
    logger.debug("getAllCompany start");
    List<Company> result= (List<Company>) repo.findAll();
    return result.stream().map(i -> CompanyMapper.companyToDTO(i)).distinct().collect(Collectors.toList());
	}
	
	public Optional<CompanyDTO> getSpecificCompany(int id) 
	{
		Logger logger = LoggerFactory.getLogger(DAOCompany.class);
	    logger.debug("get a specific Company start");
	    Company result = (Company) repo.findById((long) id).get();
	    return Optional.of(CompanyMapper.companyToDTO(result));
	}
	
	/**
	 * Used for mockito testing 
	 * @param tDAOCompany
	 */
	public void setDAOCompany(final DAOCompany tDAOCompany)
	{
		this.DAOCompany = tDAOCompany;
	}
	
	public int deleteCompany(int id) {
		DAOCompany.deleteCompany(id);
		return 0;
	}

}
