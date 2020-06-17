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

import main.java.exc.mapper.DateMapper;
import main.java.exc.model.Company;
import main.java.exc.model.Computer;

public class DAOComputer {

	private static final String ORDER_BY_COMPANY = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id ORDER BY company.name LIMIT ? OFFSET ? ";
	private static final String ORDER_BY_COMPUTER = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id ORDER BY computer.name  LIMIT ? OFFSET ? ";
	private static final String SEARCH_COMPUTER = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id WHERE LOWER(computer.name) LIKE  ? ;";
	private static final String GET_ALL_COMPUTER = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id LIMIT ? OFFSET ?";
	private static final String SEARCH_COMPUTER_BY_ID = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id where computer.id = ? ";
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	private static final String ADD_COMPUTER = "INSERT INTO computer (name,introduced, discontinued, company_id) values( ? , DATE ? , DATE ? ,(select company.id from company where company.id = ?))";
	private static final String ADD_COMPUTER_NO_DISC = "INSERT INTO computer (name,introduced, discontinued, company_id) values( ? , DATE ? , null ,(select company.id from company where company.id = ?))";
	private static final String ADD_COMPUTER_NO_DATE = "INSERT INTO computer (name,introduced, discontinued, company_id) values( ? , null , null ,(select company.id from company where company.id = ?))";
	private static final String LIST_SIZE  ="SELECT count(id) AS rowcount FROM computer";
	
	public int getCountComputer()
	{
		try(Connection conn = DataSource.getConn())
		{
			PreparedStatement statement = conn.prepareStatement(LIST_SIZE);
			ResultSet resset = statement.executeQuery();
			if (resset.next())
			{
				return resset.getInt("rowcount");
			}
			
		}
		catch (SQLException e) {
		e.printStackTrace();
		return 0;
		}
		return 0;
	}
	
	public List<Computer> getAllComputer( long offset, long limit) throws  ParseException {
		List<Computer> result = new ArrayList<>();
		try (Connection conn = DataSource.getConn()){
			List<Computer> computer = new ArrayList<>();
			PreparedStatement statement = conn.prepareStatement(GET_ALL_COMPUTER);
			statement.setLong(1, limit);
			statement.setLong(2, offset);
			System.out.println(statement);
			ResultSet resset = statement.executeQuery();
			while (resset.next()) {
				Computer tcomputer = Computer.Builder.newInstance().setId(resset.getInt("id")).setName(resset.getString("name")).build();
				if (resset.getString("introduced") != null)
					tcomputer.setIntroduced(DateMapper.StringConverter(resset.getString("introduced")).get());
				if (resset.getString("discontinued") != null)
					tcomputer.setDiscontinued(DateMapper.StringConverter(resset.getString("discontinued")).get());
				if (resset.getInt("company_id") != 0) {

					Company comp = Company.Builder.newInstance().setId(resset.getInt("company_id"))
							.setName(resset.getString("company")).build();
					tcomputer.setCompany(comp);
				} else {
					Company comp = Company.Builder.newInstance().setId(0).setName("none").build();
					tcomputer.setCompany(comp);
				}
				computer.add(tcomputer);
			}
			 result = computer;
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Computer> getAllComputerOrderBY(OrderByState Order,long offset, long limit) throws ParseException {
		List<Computer> computer = new ArrayList<>();
		try(Connection conn = DataSource.getConn()) {
			
			//Statement stmt = conn.createStatement();
			PreparedStatement statement;
			ResultSet resset;
			switch (Order) {
			case COMPANY:
				statement = conn.prepareStatement(ORDER_BY_COMPANY);
				statement.setLong(1, limit);
				statement.setLong(2, offset);
				resset = statement.executeQuery();
				break;
			case COMPUTER:
				statement = conn.prepareStatement(ORDER_BY_COMPUTER);
				statement.setLong(1, limit);
				statement.setLong(2, offset);
				resset = statement.executeQuery();
				break;
			default:
				statement = conn.prepareStatement(ORDER_BY_COMPUTER);
				statement.setLong(1, limit);
				statement.setLong(2, offset);
				resset = statement.executeQuery();
				break;
			}
			
			while (resset.next()) {
				Computer tcomputer = Computer.Builder.newInstance().setId(resset.getInt("id")).setName(resset.getString("name")).build();
				if (resset.getString("introduced") != null)
					tcomputer.setIntroduced(DateMapper.StringConverter(resset.getString("introduced")).get());
				if (resset.getString("discontinued") != null)
					tcomputer.setDiscontinued(DateMapper.StringConverter(resset.getString("discontinued")).get());
				if (resset.getInt("company_id") != 0) {

					Company comp = Company.Builder.newInstance().setId(resset.getInt("company_id"))
							.setName(resset.getString("company")).build();
					tcomputer.setCompany(comp);
				} else {
					Company comp = Company.Builder.newInstance().setId(0).setName("none").build();
					tcomputer.setCompany(comp);
				}
				computer.add(tcomputer);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return computer;
	}

	public List<Computer> Search_Computer(String name) throws  ParseException {
		List<Computer> computer = new ArrayList<>();
		try (Connection conn = DataSource.getConn()){
			
			PreparedStatement statement = conn.prepareStatement(SEARCH_COMPUTER);
			statement.setString(1, name);
			
			ResultSet resset = statement.executeQuery();
			while (resset.next()) {
				Computer tcomputer = Computer.Builder.newInstance().setName(resset.getString("name"))
						.setCompany(
								Company.Builder.newInstance().setId(resset.getInt("company_id")).setName(resset.getString("company")).build())
						.build();
				if (resset.getString("introduced") != null)
					tcomputer.setIntroduced(DateMapper.StringConverter(resset.getString("introduced")).get());
				if (resset.getString("discontinued") != null)
					tcomputer.setDiscontinued(DateMapper.StringConverter(resset.getString("discontinued")).get());
				System.out.println(tcomputer.getName());
				computer.add(tcomputer);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return computer;
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
		try( Connection conn = DataSource.getConn()) {
			
			Computer computer = Computer.Builder.newInstance().build();
			PreparedStatement statement = conn.prepareStatement(SEARCH_COMPUTER_BY_ID);
			statement.setLong(1, id);
			ResultSet resset = statement.executeQuery();
			if (resset.next()) {
				computer = Computer.Builder.newInstance().setId(resset.getInt("id")).setName(resset.getString("name")).build();
				if (resset.getString("introduced") != null)
					computer.setIntroduced(DateMapper.StringConverter(resset.getString("introduced")).get());
				if (resset.getString("discontinued") != null)
					computer.setDiscontinued(DateMapper.StringConverter(resset.getString("discontinued")).get());
				if (resset.getInt("company_id") != 0) {

					Company comp = Company.Builder.newInstance().setId(resset.getInt("company_id"))
							.setName(resset.getString("company")).build();
					computer.setCompany(comp);
				} else {
					Company comp = Company.Builder.newInstance().setId(0).setName("none").build();
					computer.setCompany(comp);}
				}
			 else {
				 return Optional.ofNullable(computer);
			}
			conn.close();
			return Optional.ofNullable(computer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();


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
