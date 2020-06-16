package main.java.exc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.exc.model.Company;

public class DAOCompany {
	private final String GET_ALL_COMPANY = "SELECT name,id FROM company";
	private final String GET_SPECIFIC_COMPANY = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name AS "
			+ "company from computer LEFT JOIN company ON computer.company_id = company.id where computer.id = ?";
	private final String DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
	private final String DELETE_COMPANY_FROM_COMPUTER = "DELETE FROM computer WHERE company_id = ?";
	/*
	 * Return the list of all companies present in the SQL database. Will throw SQLException in case it cannot access the database
	 * 
	 * 
	 *@return The list of all the company
	 *@throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public Optional<List<Company>> getAllCompany(){

		Connection conn;
		List<Company> company = new ArrayList();
		try {
			conn = DataSource.getConn();
			PreparedStatement statement = conn.prepareStatement(GET_ALL_COMPANY);
			ResultSet rs = statement.executeQuery();
	        while ( rs.next() ) {
	        	Company tcompany = Company.Builder.newInstance()
	        			.setId(rs.getInt("id"))
	        			.setName(rs.getString("name"))
	        			.build();
	            company.add(tcompany);      
	        }
	        conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Optional<List<Company>> result = Optional.ofNullable(company);
		return result;
	}
	public Optional<Company> getSpecificCompany(int id) throws SQLException, ParseException {
		if (id == 0) 
			return Optional.empty();
		Connection conn = DataSource.getConn();
		Company company = Company.Builder.newInstance().build();
		//Statement statement = conn.createStatement();
		PreparedStatement statement = conn.prepareStatement(GET_ALL_COMPANY);
		statement.setLong(1, id);
		ResultSet resset = statement.executeQuery();
		//ResultSet resset = statement.executeQuery("select computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id where computer.id =" + id);
		if (resset.next())
		{

				String name = resset.getString("name");
				int id1 = resset.getInt("id");
				String introduced = resset.getString("introduced");
				String discontinued = resset.getString("discontinued");
				int company_id = resset.getInt("company_id");
				company.setId(id);
				company.setName(name);

				
		}
		else
		{
			company = null;
		}
		conn.close();

		return Optional.ofNullable(company);
	}
	

	public int deleteCompany(int id) 
	{
		Connection conn;
		try {
			conn = DataSource.getConn();
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(DELETE_COMPANY_FROM_COMPUTER);
			statement.setLong(1, id);
			statement.executeUpdate();
			statement = conn.prepareStatement(DELETE_COMPANY);
			statement.setLong(1, id);
			statement.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			conn.close();

			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}

		return 0;
	}
}
