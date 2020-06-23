package main.java.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import main.java.excilys.mapper.RSCompanyMapper;
import main.java.excilys.model.Company;


/**
 * @author dteboul
 *Class that allows CBD to access the Database and acces information concerning companies in the db
 */
@Repository
public class DAOCompany {
	private final String GET_ALL_COMPANY = "SELECT name,id FROM company";
	private final String GET_SPECIFIC_COMPANY = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name AS "
			+ "company from computer LEFT JOIN company ON computer.company_id = company.id where computer.id = :id";
	private final String DELETE_COMPANY = "DELETE FROM company WHERE id = :id";
	private final String DELETE_COMPANY_FROM_COMPUTER = "DELETE FROM computer WHERE company_id = :id";
	
	@Autowired
	private HikariDataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private RSCompanyMapper mapper = new RSCompanyMapper();
	/*
	 * Return the list of all companies present in the SQL database. Will throw
	 * SQLException in case it cannot access the database
	 * 
	 * 
	 * @return The list of all the company
	 * 
	 * @throws SQLException
	 * 
	 * @throws ClassNotFoundException
	 */
	public List<Company> getAllCompany() {
		List<Company> company = jdbcTemplate.query(GET_ALL_COMPANY,this.mapper);
		return company;
	}

	/**
	 * Return a company that was fetched from the database 
	 * @param id
	 * @return
	 * @throws ParseException
	 */
	public Optional<Company> getSpecificCompany(int id) throws ParseException {
		if (id == 0)
			return Optional.empty();
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
		Company company = jdbcTemplate.queryForObject(GET_SPECIFIC_COMPANY, this.mapper,params);
		return Optional.of(company);
	}

	/**
	 * Delete a company from the database, including every computer that was connected to it. The autocommit is set to false to prevent manipulation that would
	 * make the database unusable
	 * @param id
	 * @return
	 */
	public int deleteCompany(int id) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
		jdbcTemplate.update(DELETE_COMPANY_FROM_COMPUTER, this.mapper,params);
		jdbcTemplate.update(DELETE_COMPANY, this.mapper,params);
		return 1;
	}
}
