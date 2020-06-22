package main.java.excilys.persistence;

import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import main.java.excilys.model.Company;

public interface DAOCompanyInterface {
	public Optional<List<Company>> getAllCompany() throws SQLException;

}
