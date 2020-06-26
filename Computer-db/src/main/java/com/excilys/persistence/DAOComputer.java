package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.DateMapper;
import com.excilys.mapper.RSCompanyMapper;
import com.excilys.mapper.RSComputerMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author dteboul
 *Class that allows CBD to access the Database and acces information concerning computer in the db
 */
@Repository
public class DAOComputer {

	private static final String ORDER_BY_COMPANY = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id ORDER BY company.name LIMIT :limit OFFSET :offset ;";
	private static final String ORDER_BY_COMPUTER = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id ORDER BY computer.name  LIMIT :limit OFFSET :offset ;";
	private static final String SEARCH_COMPUTER = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id WHERE LOWER(computer.name) LIKE  :like ;";
	private static final String GET_ALL_COMPUTER = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id LIMIT :limit OFFSET :offset ;";
	private static final String SEARCH_COMPUTER_BY_ID = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id where computer.id = :id ;";
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id = :id;";
	private static final String ADD_COMPUTER = "INSERT INTO computer (name,introduced, discontinued, company_id) values( :name , DATE :introduced , DATE :discontinued ,(select company.id from company where company.id = :id))";
	private static final String ADD_COMPUTER_NO_DISC = "INSERT INTO computer (name,introduced, discontinued, company_id) values( :name , DATE :introduced , null ,(select company.id from company where company.id = :id));";
	private static final String ADD_COMPUTER_NO_DATE = "INSERT INTO computer (name,introduced, discontinued, company_id) values( :name , null , null ,(select company.id from company where company.id = :id));";
	private static final String LIST_SIZE  ="SELECT count(id) AS rowcount FROM computer";
	private static final String UPDATE_COMPUTER ="UPDATE computer SET name = :name , introduced = :introduced , discontinued = :discontinued , company_id = :company_id WHERE id = id;";
	
	
	@Autowired
	private HikariDataSource dataSource;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	private RSComputerMapper mapper = new RSComputerMapper();
	/**
	 * Get the number of computers object that are currently in the database.
	 * @return
	 */
	public int getCountComputer()
	{
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("count", "");
		return jdbcTemplate.queryForObject(LIST_SIZE, Collections.emptyMap(),(res, rowNum) -> res.getInt(1));

	}
	
	/**
	 * 
	 * Get a set number of computers, limited by the offset and the limit to allow pagination servlet side, and to prevent an overload of the server
	 * @param offset
	 * @param limit
	 * @return
	 * @throws ParseException
	 */
	public List<Computer> getAllComputer( int  offset, int limit) throws  ParseException {
		System.out.println("offset = " + offset + ".  limit = " + limit );
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("offset", offset).addValue("limit", limit);
		return jdbcTemplate.query(GET_ALL_COMPUTER,params,this.mapper);
	}

	/**
	 * 
	 * Get a set number of computers, limited by the offset and the limit to allow pagination servlet side, and to prevent an overload of the server, using an order by 
	 * to sort the output
	 * @param Order
	 * @param offset
	 * @param limit
	 * @return
	 * @throws ParseException
	 */
	public List<Computer> getAllComputerOrderBY(OrderByState Order,long offset, long limit) throws ParseException {
		SqlParameterSource params = new MapSqlParameterSource().addValue("offset", offset).addValue("limit", limit);
		switch (Order)
		{
			case COMPANY:
				return jdbcTemplate.query(ORDER_BY_COMPANY,params,this.mapper);
			case COMPUTER:
				return jdbcTemplate.query(ORDER_BY_COMPUTER,params,this.mapper);
			default:
				return jdbcTemplate.query(ORDER_BY_COMPUTER,params,this.mapper);
		}
	}

	/**
	 * return a list of computer that matches the input
	 * @param name
	 * @return
	 * @throws ParseException
	 */
	public List<Computer> Search_Computer(String name) throws  ParseException {
		SqlParameterSource params = new MapSqlParameterSource().addValue("like", name);
		return jdbcTemplate.query(SEARCH_COMPUTER,params,this.mapper);
	}

	/**
	 * Search a single computer in the database using its id.
	 * 
	 * @param id the ID of the computer to search in the database
	 * @return the computer if it exist
	 * @throws SQLException
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 */
	public Optional<Computer> get_Computer_By_Id(int id) throws ParseException {

		if (id == 0)
			return Optional.empty();
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
		return Optional.ofNullable(jdbcTemplate.queryForObject(SEARCH_COMPUTER_BY_ID,params,this.mapper));
	}

	/**
	 * Delete a single computer in the database using its id.
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int deleteSpecificComputer(int id) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
		return jdbcTemplate.update(DELETE_COMPUTER,params);


	}

	/**
	 * Try to add a new computer to the database 
	 * 
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param id_company
	 * @return
	 */
	public int addComputer(String name, String introduced, String discontinued, long id_company) {
		if ((introduced == null) && (discontinued == null))
		{
			SqlParameterSource params = new MapSqlParameterSource().addValue("name", name).addValue("id", id_company);
			return jdbcTemplate.update(ADD_COMPUTER_NO_DATE,params);			
		}
		else if ( discontinued == null)
		{
			SqlParameterSource params = new MapSqlParameterSource().addValue("name", name).addValue("id", id_company).addValue("introduced", introduced);
			return jdbcTemplate.update(ADD_COMPUTER_NO_DISC,params);			
		}
		else
		{
			SqlParameterSource params = new MapSqlParameterSource().addValue("name", name).addValue("id", id_company).addValue("introduced", introduced).addValue("discontinued", discontinued);
			return jdbcTemplate.update(ADD_COMPUTER,params);
		}

	}

	/**
	 * Method that update a single computer within its Id
	 * 
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param l
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int updateComputer(String name, String introduced, String discontinued, long id_company, int id)  {
		SqlParameterSource params = new MapSqlParameterSource().addValue("name", name).addValue("company_id", id_company).addValue("introduced", introduced).addValue("discontinued", discontinued).addValue("id", id);
		return jdbcTemplate.update(ADD_COMPUTER,params);

	}
}
