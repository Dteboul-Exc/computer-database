package main.java.exc.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.exc.mapper.CompanyMapper;
import main.java.exc.mapper.ComputerMapper;
import main.java.exc.mapper.DateMapper;
import main.java.exc.model.Company;
import main.java.exc.model.CompanyDTO;
import main.java.exc.model.Computer;
import main.java.exc.model.ComputerDTO;
import main.java.exc.persistence.DAOCompany;
import main.java.exc.persistence.DAOComputer;
import main.java.exc.persistence.OrderByState;
import main.java.exc.persistence.SQLConnect;

public class ServiceComputer {
	private static final Logger lOG =
            LoggerFactory.getLogger(ServiceComputer.class);
	private DAOComputer DAOComputer = new DAOComputer();
	public ServiceComputer() {
		DAOComputer = new DAOComputer();
	}
	public ServiceComputer(DAOComputer c) {
		this.DAOComputer = c;
	}
	public void set_ComputerValidator(DAOComputer DAO)
	{
		this.DAOComputer = DAO;
	}
	public List<ComputerDTO> getAllComputer()  {
		BasicConfigurator.configure();

	    lOG.debug("getAllComputer start");
	   
	    List<Computer> dataset;
	    List<ComputerDTO> result = new ArrayList<>();
	    try {
			dataset = DAOComputer.getAllComputer();
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
		BasicConfigurator.configure();

	    lOG.debug("getSpecificComputer start using computer id : "+id);
	    Optional<ComputerDTO> result;
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
		BasicConfigurator.configure(); 

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
		BasicConfigurator.configure();
	    lOG.debug("getSpecificComputer start using computer id : "+id);
	    int result;
	    result = DAOComputer.deleteSpecificComputer(id);
		return result;  
	}
	public int addComputer(ComputerDTO computer)  
	{
		BasicConfigurator.configure();
	    lOG.debug("addComputer start using computer");
		int computer_id = 2;
		int resset=0;
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
			tintroduced = null;
		}
		else
		{
			tintroduced =  computer.getIntroduced();
		}
		if (computer.getDiscontinued().equals(""))
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
		BasicConfigurator.configure();
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
	
	
	public List<ComputerDTO> getAllComputerOrderBy(OrderByState state)  {
		BasicConfigurator.configure();

	    lOG.debug("getAllComputer start");
	    List<ComputerDTO> result = new ArrayList<>();
	    List<Computer> dataset = null;
	    try {
			dataset = DAOComputer.getAllComputerOrderBY(state);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
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
