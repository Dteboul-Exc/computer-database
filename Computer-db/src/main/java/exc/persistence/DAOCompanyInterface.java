package main.java.exc.persistence;

import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import main.java.exc.model.Company;

public interface DAOCompanyInterface {
	public Optional<List<Company>> getAllCompany() throws SQLException;

}
