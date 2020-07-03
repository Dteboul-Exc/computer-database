package com.excilys.service;

import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.excilys.CrudRepository.QueryComputerInterface;
import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ServiceComputerException;
import com.excilys.mapper.CompanyMapper;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;
import com.excilys.CrudRepository.DAOComputer;

/**
 * @author dteboul
 *Assure the link between the servlet and the DAO.
 */
@Service
public class ServiceComputer {
	private static final Logger lOG =
            LoggerFactory.getLogger(ServiceComputer.class);
	@Autowired QueryComputerInterface repo;
	
	@Autowired
	private DAOComputer DAOComputer;
	
	public ServiceComputer() {
	}
	
	public ServiceComputer(DAOComputer c) {
		this.DAOComputer = c;
	}
	public void set_ComputerValidator(DAOComputer DAO)
	{
		this.DAOComputer = DAO;
	}
	public int getCountComputer()
	{
		lOG.debug("Getting rowcount of the computer db");
		return (int) repo.count();
	}
	public List<ComputerDTO> getAllComputer(int offset, int limit)  {

	    lOG.debug("getAllComputer start");
	    Pageable page = PageRequest.of(offset, limit);
	    List<Computer> result = (List<Computer>) repo.findAll(page);
	   return result.stream().map(i -> ComputerMapper.computerToDTO(i).get()).distinct().collect(Collectors.toList());
	}
	public  Optional<ComputerDTO> getSpecificComputer(int id)  {

	    lOG.debug("getSpecificComputer start using computer id : "+id);
	    Computer result = (Computer) repo.findById((long) id).get();
	    return ComputerMapper.computerToDTO(result);
	}
/*	
	public  List<ComputerDTO> Search_Computer(String name)  {
		

	    lOG.debug("Search_Computer start using computer name : "+name);
	  //  List<Computer> result = repo.findByNameContainingIgnoreCase(name);
	  //  return result.stream().map(i -> ComputerMapper.computerToDTO(i).get()).distinct().collect(Collectors.toList());
	}*/
	public int deleteSpecificComputer(int id) 
	{
		
	    lOG.debug("getSpecificComputer start using computer id : "+id);

		 repo.deleteById((long) id);  
		 return 1;
	}
	public int addComputer(ComputerDTO computer)  
	{
	    lOG.debug("addComputer start using computer");
		String tname = "NULL";
		String tintroduced = "NULL";
		String tdiscontinued = "NULL";
		if (computer.getName() == null)
		{
			lOG.error("Error, name was null");
			return -1;
		}
		else
		{
			tname = computer.getName();
		}
		if (computer.getIntroduced().equals("") || computer.getIntroduced().equals(null) )
		{
			tintroduced = null;
		}
		else
		{
			tintroduced =  computer.getIntroduced();
		}
		if (computer.getDiscontinued().equals("") || computer.getDiscontinued().equals(null))
		{
			tdiscontinued = null;
		}
		else
		{
			tdiscontinued =  computer.getDiscontinued();
		}
		int result  = DAOComputer.addComputer(tname,tintroduced,tdiscontinued,Integer.parseInt(computer.getCompany().getId()));
		return result;		
	}
	public int updateComputer(ComputerDTO computer)  
	{
	    lOG.debug("updateComputer start using computer");
		String tname = "NULL";
		String tintroduced = "NULL";
		String tdiscontinued = "NULL";
		repo.save(ComputerMapper.CDTOToComputer(computer).get());
		/*Statement statement;
		if (computer.getName() == null)
		{
			lOG.error("Error, name was null");
			return -1;
		}
		else
		{
			tname = computer.getName();
		}
		if (computer.getIntroduced().equals(""))
		{
			tintroduced = "NULL";
		}
		else
		{
			tintroduced = computer.getIntroduced();
		}
		if (computer.getDiscontinued().equals(""))
		{
			tdiscontinued = "NULL";
		}
		else
		{
			tdiscontinued = computer.getDiscontinued();;
		}
		int result  = DAOComputer.updateComputer(tname,tintroduced,tdiscontinued,Long.parseLong(computer.getCompany().getId()),Integer.parseInt(computer.getId()));*/
		return 1;
	}
	
	
	public List<ComputerDTO> getAllComputerOrderBy(String state,int offset, int limit)  {

	    lOG.debug("getAllComputer start");
	   /* List<ComputerDTO> result = new ArrayList<>();
	    List<Computer> dataset = null;
	    try {
			dataset = DAOComputer.getAllComputerOrderBY(state,offset,limit);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	    
	    for(Computer computer : dataset)
			result.add(ComputerMapper.computerToDTO(computer).get());
		return result;*/
	    Pageable page = PageRequest.of(offset, limit,Direction.ASC,state);
	    List<Computer> result = (List<Computer>) repo.findAll(page);
	   return result.stream().map(i -> ComputerMapper.computerToDTO(i).get()).distinct().collect(Collectors.toList());
	}
	
}
