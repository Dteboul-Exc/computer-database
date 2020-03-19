package main.java.exc.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.exc.model.Company;
import main.java.exc.persistence.DAOCompany;

public class ServiceDAOCompany {
	public static Optional<List<Company>> getAllCompany() {
	BasicConfigurator.configure();
	Logger logger = LoggerFactory.getLogger(DAOCompany.class);
    logger.debug("getAllCompany start");
    Optional<List<Company>> result;
    try {
		result = DAOCompany.getAllCompany();
		return result;
	} catch (SQLException e) {
		logger.error("error while getting all Company : "+ e);
		e.printStackTrace();
		return result = Optional.empty();
	}
	}
	
	public static Optional<Company> getSpecificCompany(int id)
	{
		BasicConfigurator.configure();
		Logger logger = LoggerFactory.getLogger(DAOCompany.class);
	    logger.debug("getA Company start");
	    Optional<Company> result;
	    try {
			result = DAOCompany.getSpecificCompany(id);
			return result;
		} catch (SQLException e) {
			logger.error("error while getting all Computers : "+ e);
			e.printStackTrace();
			return result = Optional.empty();
		} catch (ParseException e) {
			logger.error("error while getting all Computers : "+ e);
			return result = Optional.empty();
		}
	}
	

}
