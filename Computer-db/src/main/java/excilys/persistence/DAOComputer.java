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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariDataSource;

import main.java.excilys.mapper.DateMapper;
import main.java.excilys.mapper.RSCompanyMapper;
import main.java.excilys.mapper.RSComputerMapper;
import main.java.excilys.model.Company;
import main.java.excilys.model.Computer;

/**
 * @author dteboul
 *Class that allows CBD to access the Database and acces information concerning computer in the db
 */
@Repository
public class DAOComputer {

	private static final String ORDER_BY_COMPANY = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id ORDER BY company.name LIMIT :limit OFFSET :offset ";
	private static final String ORDER_BY_COMPUTER = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id ORDER BY computer.name  LIMIT :limit OFFSET :offset ";
	private static final String SEARCH_COMPUTER = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id WHERE LOWER(computer.name) LIKE  :like ;";
	private static final String GET_ALL_COMPUTER = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id LIMIT :limit OFFSET :offset ";
	private static final String SEARCH_COMPUTER_BY_ID = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id where computer.id = :id ";
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	private static final String ADD_COMPUTER = "INSERT INTO computer (name,introduced, discontinued, company_id) values( ? , DATE ? , DATE ? ,(select company.id from company where company.id = :id))";
	private static final String ADD_COMPUTER_NO_DISC = "INSERT INTO computer (name,introduced, discontinued, company_id) values( ? , DATE ? , null ,(select company.id from company where company.id = :id))";
	private static final String ADD_COMPUTER_NO_DATE = "INSERT INTO computer (name,introduced, discontinued, company_id) values( ? , null , null ,(select company.id from company where company.id = :id))";
	private static final String LIST_SIZE  ="SELECT count(id) AS rowcount FROM computer";
	
	
	@Autowired
	private HikariDataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private RSComputerMapper mapper = new RSComputerMapper();
	/**
	 * Get the number of computers object that are currently in the database.
	 * @return
	 */
	public int getCountComputer()
	{
		return jdbcTemplate.queryForObject(LIST_SIZE,Integer.class);
	}
	
	/**
	 * 
	 * Get a set number of computers, limited by the offset and the limit to allow pagination servlet side, and to prevent an overload of the server
	 * @param offset
	 * @param limit
	 * @return
	 * @throws ParseException
	 */
	public List<Computer> getAllComputer( long offset, long limit) throws  ParseException {
		SqlParameterSource params = new MapSqlParameterSource().addValue("offset", offset).addValue("limit", limit);
		return jdbcTemplate.query(GET_ALL_COMPUTER,this.mapper,params);
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
				return jdbcTemplate.query(ORDER_BY_COMPANY,this.mapper,params);
			case COMPUTER:
				return jdbcTemplate.query(ORDER_BY_COMPUTER,this.mapper,params);
			default:
				return jdbcTemplate.query(ORDER_BY_COMPUTER,this.mapper,params);
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
		return jdbcTemplate.query(SEARCH_COMPUTER,this.mapper,params);
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
		return Optional.ofNullable(jdbcTemplate.queryForObject(SEARCH_COMPUTER_BY_ID,this.mapper,params));
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

		try (Connection conn = DataSource.getConn();){
			PreparedStatement statement = conn.prepareStatement(DELETE_COMPUTER);
			statement.setLong(1, id);
			ResultSet resset = statement.executeQuery();
			conn.close();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

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
		System.out.print(name);
		try (Connection conn = DataSource.getConn()){
			PreparedStatement statement = conn.prepareStatement(ADD_COMPUTER);
			if ((introduced == null) && (discontinued == null))
			{	statement = conn.prepareStatement(ADD_COMPUTER_NO_DATE);
				statement.setString(1, name);
				statement.setLong(2, id_company);}
			else if (discontinued == null)
			{	statement = conn.prepareStatement(ADD_COMPUTER_NO_DISC);
			statement.setString(1, name);
			statement.setString(2, introduced);
			statement.setLong(3, id_company);}
			else
			{
				statement.setString(1, name);
				statement.setString(2, introduced);
				statement.setLong(4, id_company);
				statement.setString(3, discontinued);
			}	
			int resset = statement.executeUpdate();
			conn.close();
			return resset;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
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
	public int updateComputer(String name, String introduced, String discontinued, long l, int id)  {
		
		int resset = 0;

		Statement statement;

		try(Connection conn = DataSource.getConn()) {
			statement = conn.createStatement();
			System.out.println("UPDATE computer SET name = '" + name + "', introduced = " + introduced + ", discontinued = "
					+ discontinued + ", company_id = " + l + " WHERE id = " + id);
			resset = statement.executeUpdate("UPDATE computer SET name = '" + name + "', introduced = " + introduced
					+ ", discontinued = " + discontinued + ", company_id = " + l + " WHERE id = " + id);
			System.out.println(resset);

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;

	}
}
