package com.excilys.CrudRepository;

import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.excilys.model.Company;

public interface DAOCompanyInterface {
	public Optional<List<Company>> getAllCompany() throws SQLException;

}
