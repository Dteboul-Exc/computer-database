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
import main.java.exc.mapper.DateMapper;
import main.java.exc.model.Company;
import main.java.exc.model.Computer;
import main.java.exc.persistence.DAOCompany;
import main.java.exc.persistence.DAOComputer;
import main.java.exc.persistence.SQLConnect;

public class ServiceComputer {
	private static final Logger lOG =
            LoggerFactory.getLogger(ServiceComputer.class);
	public ServiceComputer() {
		
	}
	
	public Optional<List<ComputerDTO>> getAllComputer() {
		BasicConfigurator.configure();

	    lOG.debug("getAllComputer start");
	    Optional<List<Computer>> dataset;
	    try {
			dataset = DAOComputer.getAllComputer();
			for(Computer company : dataset.get())
				result.add(CompanyMapper.companyToDTO(company));
			return Optional.of(result);
			return result;
		} catch (SQLException e) {
			lOG.error("SQLerror while getting all Computer : "+ e);
			e.printStackTrace();
			return Optional.empty();
		} catch (ParseException e) {
			lOG.error("ParseException while getting all Computer : "+ e);
			e.printStackTrace();
			return Optional.empty();
		}
	}
	public  Optional<Computer> getSpecificComputer(int id) {
		BasicConfigurator.configure();

	    lOG.debug("getSpecificComputer start using computer id : "+id);
	    Optional<Computer> result;
	    try {
			result = DAOComputer.getSpecificComputer(id);
			return result;
		} catch (SQLException e) {
			lOG.error("SQLerror while getting Computer with id "+id+"error: "+ e);
			e.printStackTrace();
			return result = Optional.empty();
		} catch (ParseException e) {
			lOG.error("ParseException while getting  Computer id " + id+ "error : "+ e);
			e.printStackTrace();
			return result = Optional.empty();
		}
	}
	public static int deleteSpecificComputer(int id)
	{
		BasicConfigurator.configure();
	    lOG.debug("getSpecificComputer start using computer id : "+id);
	    int result;
	    try {
			result = DAOComputer.deleteSpecificComputer(id);
			return result;
		} catch (SQLException e) {
			lOG.error("SQL error while deleting Computer with id "+id+"error: "+ e);
			e.printStackTrace();
			return result = 0;
		}  
	}
	public int addComputer(String name,String introduced,String discontinued, int company_id) 
	{
		BasicConfigurator.configure();
	    lOG.debug("addComputer start using computer");
		int computer_id = 2;
		int resset=0;
		String tname = "NULL";
		String tintroduced = "NULL";
		String tdiscontinued = "NULL";

		Statement statement;
		try {
			if (name == null)
			{
				tname = "NULL";
			}
			else
			{
				tname = name;
			}
			if (introduced.equals("NULL"))
			{
				tintroduced = "NULL";
			}
			else
			{
				tintroduced = "DATE " + "'"+introduced+"'";
			}
			if (discontinued.equals("NULL"))
			{
				tdiscontinued = "NULL";
			}
			else
			{
				tdiscontinued = "DATE " + "'"+discontinued+"'";;
			}
			System.out.print(name);
			int result  = DAOComputer.addComputer(name,tintroduced,tdiscontinued,company_id);
			return result;
	
		} catch (SQLException exc) {
			 lOG.error("SQL Error while creating a new computer : " + exc);
			exc.printStackTrace();
			return 1;
		}		
	}
	public int updateComputer(String name,String introduced,String discontinued, long l,int id) 
	{
		BasicConfigurator.configure();
	    lOG.debug("updateComputer start using computer");
		int computer_id = 2;
		int resset=0;
		String tname = "NULL";
		String tintroduced = "NULL";
		String tdiscontinued = "NULL";

		Statement statement;
		try {
			if (name == null)
			{
				tname = "NULL";
			}
			else
			{
				tname = name;
			}
			if (introduced.equals("NULL"))
			{
				tintroduced = "NULL";
			}
			else
			{
				tintroduced = "DATE " + "'"+introduced+"'";
			}
			if (discontinued.equals("NULL"))
			{
				tdiscontinued = "NULL";
			}
			else
			{
				tdiscontinued = "DATE " + "'"+discontinued+"'";;
			}
			int result  = DAOComputer.updateComputer(name,tintroduced,tdiscontinued,l,id);
			return result;
	
		} catch (SQLException exc) {
			 lOG.error("SQL Error while creating a new computer : " + exc);
			exc.printStackTrace();
			return 1;
		}
	}
	
}
