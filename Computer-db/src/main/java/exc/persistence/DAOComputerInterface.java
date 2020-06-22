package main.java.exc.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import main.java.exc.model.Company;
import main.java.exc.model.Computer;


public interface DAOComputerInterface {
	public List<Computer> getAllComputer() throws SQLException;
	Optional<Computer> getSpecificComputer(int id) throws SQLException;
	public int addComputer(String name,String introduced,String discontinued, int company_id);
	public int updateComputer(String name,String introduced,String discontinued, int company_id,int id);
	

}
