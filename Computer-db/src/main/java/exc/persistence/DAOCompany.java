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

import org.springframework.stereotype.Component;

import main.java.exc.model.Company;


@Component
public class DAOCompany {
	private final String GET_ALL_COMPANY = "SELECT name,id FROM company";
	private final String GET_SPECIFIC_COMPANY = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name AS "
			+ "company from computer LEFT JOIN company ON computer.company_id = company.id where computer.id = ?";
	private final String DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
	private final String DELETE_COMPANY_FROM_COMPUTER = "DELETE FROM computer WHERE company_id = ?";

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


		List<Company> company = new ArrayList<>();
		try (Connection conn = DataSource.getConn()){
			
			PreparedStatement statement = conn.prepareStatement(GET_ALL_COMPANY);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Company tcompany = Company.Builder.newInstance().setId(rs.getInt("id")).setName(rs.getString("name"))
						.build();
				company.add(tcompany);
			}
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Company> result = company;
		return result;
	}

	public Optional<Company> getSpecificCompany(int id) throws ParseException {
		if (id == 0)
			return Optional.empty();
		Connection conn;
		try {
			conn = DataSource.getConn();
			Company company = Company.Builder.newInstance().build();
			PreparedStatement statement = conn.prepareStatement(GET_ALL_COMPANY);
			statement.setLong(1, id);
			ResultSet resset = statement.executeQuery();
			if (resset.next()) {

				String name = resset.getString("name");
				company.setId(id);
				company.setName(name);
			} else {
				company = null;
			}
			conn.close();
			return Optional.ofNullable(company);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Optional.empty();
	}

	public int deleteCompany(int id) {
		try (Connection conn = DataSource.getConn()){
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
			e.printStackTrace();
		}
		return 0;
	}
}
