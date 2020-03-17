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

import main.java.exc.mapper.DateMapper;
import main.java.exc.model.Company;
import main.java.exc.model.Computer;
import main.java.exc.persistence.DAOCompany;
import main.java.exc.persistence.DAOComputer;
import main.java.exc.persistence.SQLConnect;

public class ServiceDAOComputer {
	
	public static Optional<List<Computer>> getAllComputer() {
		BasicConfigurator.configure();
		Logger logger = LoggerFactory.getLogger(ServiceDAOComputer.class);
	    logger.debug("getAllComputer start");
	    Optional<List<Computer>> result;
	    try {
			result = DAOComputer.getAllComputer();
			return result;
		} catch (SQLException e) {
			logger.error("SQLerror while getting all Computer : "+ e);
			e.printStackTrace();
			return result = Optional.empty();
		} catch (ParseException e) {
			logger.error("ParseException while getting all Computer : "+ e);
			e.printStackTrace();
			return result = Optional.empty();
		}
	}
	public static Optional<Computer> getSpecificComputer(int id) {
		BasicConfigurator.configure();
		Logger logger = LoggerFactory.getLogger(ServiceDAOComputer.class);
	    logger.debug("getSpecificComputer start using computer id : "+id);
	    Optional<Computer> result;
	    try {
			result = DAOComputer.getSpecificComputer(id);
			return result;
		} catch (SQLException e) {
			logger.error("SQLerror while getting Computer with id "+id+"error: "+ e);
			e.printStackTrace();
			return result = Optional.empty();
		} catch (ParseException e) {
			logger.error("ParseException while getting  Computer id " + id+ "error : "+ e);
			e.printStackTrace();
			return result = Optional.empty();
		}
	}
	public static int deleteSpecificComputer(int id)
	{
		BasicConfigurator.configure();
		Logger logger = LoggerFactory.getLogger(ServiceDAOComputer.class);
	    logger.debug("getSpecificComputer start using computer id : "+id);
	    int result;
	    try {
			result = DAOComputer.deleteSpecificComputer(id);
			return result;
		} catch (SQLException e) {
			logger.error("SQL error while deleting Computer with id "+id+"error: "+ e);
			e.printStackTrace();
			return result = 0;
		}  
	}
	public static int addComputer(String name,String introduced,String discontinued, int company_id) 
	{
		BasicConfigurator.configure();
		Logger logger = LoggerFactory.getLogger(ServiceDAOComputer.class);
	    logger.debug("addComputer start using computer");
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
			int result  = DAOComputer.addComputer(name,tintroduced,tdiscontinued,company_id);
			return result;
	
		} catch (SQLException exc) {
			 logger.error("SQL Error while creating a new computer : " + exc);
			exc.printStackTrace();
			return 1;
		}		
	}
	public static int updateComputer(String name,String introduced,String discontinued, int company_id,int id) 
	{
		BasicConfigurator.configure();
		Logger logger = LoggerFactory.getLogger(ServiceDAOComputer.class);
	    logger.debug("updateComputer start using computer");
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
			int result  = DAOComputer.updateComputer(name,tintroduced,tdiscontinued,company_id,id);
			return result;
	
		} catch (SQLException exc) {
			 logger.error("SQL Error while creating a new computer : " + exc);
			exc.printStackTrace();
			return 1;
		}
	}
	
}
