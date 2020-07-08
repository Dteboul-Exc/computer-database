package main.java.excilys.service;

import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import main.java.excilys.dto.ComputerDTO;
import main.java.excilys.exception.ServiceComputerException;
import main.java.excilys.mapper.ComputerMapper;
import main.java.excilys.model.Computer;
import main.java.excilys.persistence.DAOComputer;
import main.java.excilys.persistence.OrderByState;


@Service
public class ServiceComputer {
	private static final Logger lOG =
            LoggerFactory.getLogger(ServiceComputer.class);
	
	
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
		return DAOComputer.getCountComputer();
	}
	public List<ComputerDTO> getAllComputer(long offset, long limit)  {



	    lOG.debug("getAllComputer start");
	   
	    List<Computer> dataset;
	    List<ComputerDTO> result = new ArrayList<>();
	    try {
			dataset = DAOComputer.getAllComputer(offset,limit);
			for(Computer computer : dataset)
				result.add(ComputerMapper.computerToDTO(computer).get());
			return result;
		} catch (ParseException e) {
			lOG.error("ParseException while getting all Computer : "+ e);
			e.printStackTrace();
			return result;
		}
	}
	public  Optional<ComputerDTO> getSpecificComputer(int id)  {

	    lOG.debug("getSpecificComputer start using computer id : "+id);
	    Optional<ComputerDTO> result;
	    
	    try {
			ComputerValidator.isComputerId(id);
		} catch (ServiceComputerException e1) {
			e1.printStackTrace();
			return result = Optional.empty();
		}
	    try {
			result = ComputerMapper.computerToDTO(DAOComputer.get_Computer_By_Id(id).get());
			return result;
		} catch (ParseException e) {
			lOG.error("ParseException while getting  Computer id " + id+ "error : "+ e);
			e.printStackTrace();
			return result = Optional.empty();
		}
	}
	
	public  List<ComputerDTO> Search_Computer(String name)  {


	    lOG.debug("Search_Computer start using computer name : "+name);
	    List<Computer> dataset;
	    List<ComputerDTO> result = new ArrayList<>();
	    try {
			dataset = DAOComputer.Search_Computer("%" +name+"%");
			for(Computer computer : dataset)
				result.add(ComputerMapper.computerToDTO(computer).get());
			return result;
		} catch (ParseException e) {
			lOG.error("ParseException Searching for the Computers : "+ e);
			e.printStackTrace();
			return result;
		}
	}
	public int deleteSpecificComputer(int id) 
	{
	    lOG.debug("getSpecificComputer start using computer id : "+id);
	    int result;
	    result = DAOComputer.deleteSpecificComputer(id);
		return result;  
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

		Statement statement;
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
			tintroduced = "DATE " + "'"+computer.getIntroduced()+"'";
		}
		if (computer.getDiscontinued().equals(""))
		{
			tdiscontinued = "NULL";
		}
		else
		{
			tdiscontinued = "DATE " + "'"+computer.getDiscontinued()+"'";;
		}
		int result  = DAOComputer.updateComputer(tname,tintroduced,tdiscontinued,Long.parseLong(computer.getCompany().getId()),Integer.parseInt(computer.getId()));
		return result;
	}
	
	
	public List<ComputerDTO> getAllComputerOrderBy(OrderByState state,long offset, long limit)  {

	    lOG.debug("getAllComputer start");
	    List<ComputerDTO> result = new ArrayList<>();
	    List<Computer> dataset = null;
	    try {
			dataset = DAOComputer.getAllComputerOrderBY(state,offset,limit);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	    
	    try {
			
			for(Computer computer : dataset)
				result.add(ComputerMapper.computerToDTO(computer).get());
			return result;
		} catch (ParseException e) {
			lOG.error("ParseException while getting all Computer : "+ e);
			e.printStackTrace();
			return result;
			
		}
	}
	
}
