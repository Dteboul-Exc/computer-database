package exc.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exc.mapper.DateMapper;
import exc.model.Company;
import exc.model.Computer;

public  final class SQLConnect implements DAOCompanyInterface {
	private static SQLConnect instance = new SQLConnect();
	private static   Connection conn;
	public  static Connection getConn() {
		return conn;
	}

	private   Connection connection;

	public static SQLConnect getInstance() {
		return instance;
	}

	private SQLConnect() {}
	/**
	 * Method that allow a connection to the SQL Database
	 * @throws SQLException
	 */
	public  void  connect() throws SQLException
	{
		String url = "jdbc:mysql://localhost:3306/";
		String username = "admincdb";
		String password = "qwerty1234";
		String db = "computer-database-db";
		conn = DriverManager.getConnection(url+db, username, password);
        try {
        	connection = DriverManager.getConnection(url, username, password);
        	System.out.println("Connection established successfully!");
        }
        catch (SQLException e) {
        	throw new IllegalStateException("Unable to connect to the database. " + e.getMessage());
        }
	}

	
	/**
	 * Return the list of all companies present in the SQL database. Will throw SQLException in case it cannot access the database
	 * 
	 * 
	 *@return The list of all the company
	 *@throws SQLException
	 */
	public Optional<List<Company>> getAllCompany() throws SQLException {
		List<Company> company = new ArrayList();
		//Optional<List<Company>> company = Optional.of(new ArrayList());
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select name,id from company");
        while ( rs.next() ) {
        	Company tcompany = Company.Builder.newInstance().build();
            String name = rs.getString("name");
            int id = rs.getInt("id");
            tcompany.setId(id);
            tcompany.setName(name);
            company.add(tcompany);
            
        }
        Optional<List<Company>> result = Optional.ofNullable(company);
		return result;
	}
	
	/**
	 * Return the list of all computers present in the SQL database. Will throw SQLException in case it cannot access the database
	 * 
	 * 
	 *@return The list of all the computers
	 *@throws SQLException
	 * @throws ParseException 
	 */
	public Optional<List<Computer>> getAllComputer() throws SQLException, ParseException{
		List<Computer> computer = new ArrayList();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id");
        while ( rs.next() ) {
        	Computer tcomputer = Computer.Builder.newInstance().build();
            String name = rs.getString("name");
            String company = rs.getString("company");
            int id = rs.getInt("id");
            String introduced = rs.getString("introduced");
            String discontinued = rs.getString("discontinued");
            int company_id = rs.getInt("company_id");
            tcomputer.setId(id);
            tcomputer.setName(name);
			if(introduced != null) tcomputer.setIntroduced(DateMapper.StringConverter(introduced).get());
			if(discontinued != null) tcomputer.setDiscontinued(DateMapper.StringConverter(discontinued).get());
			if(company_id!=0)
				{
				
				Company comp = Company.Builder.newInstance().setId(rs.getInt("company_id")).setName(rs.getString("company")).build();
				tcomputer.setCompany(comp);
				}
				else
				{
					Company comp = Company.Builder.newInstance().setId(0).setName("none").build();
					tcomputer.setCompany(comp);
				}
            computer.add(tcomputer);
            
        }
        Optional<List<Computer>> result = Optional.ofNullable(computer);
		return result;
	}
	
	/**
	 * Search a single computer in the database using its id.
	 * 
	 * @param id the ID of the computer to search in the database
	 * @return the computer if it exist
	 * @throws SQLException
	 * @throws ParseException 
	 */
	public Optional<Computer> getSpecificComputer(int id) throws SQLException, ParseException {
		Computer computer = Computer.Builder.newInstance().build();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id where computer.id =" + id);
		if (rs.next())
		{

				String name = rs.getString("name");
				int id1 = rs.getInt("id");
				String introduced = rs.getString("introduced");
				String discontinued = rs.getString("discontinued");
				int company_id = rs.getInt("company_id");
				computer.setId(id);
				computer.setName(name);
				if(introduced != null) computer.setIntroduced(DateMapper.StringConverter(introduced).get());
				if(discontinued != null) computer.setDiscontinued(DateMapper.StringConverter(discontinued).get());
				if(company_id!=0)
				{
					Company comp = Company.Builder.newInstance().setId(rs.getInt("company_id")).setName(rs.getString("company")).build();
					computer.setCompany(comp);
				}
				
		}
		else
		{
			computer = null;
		}
		System.out.println("id = " + computer.getId());
		return Optional.ofNullable(computer);
	}
	
	
	/**
	 * Delete a single computer in the database using its id.
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deleteSpecificComputer(int id) throws SQLException {
		Statement statement = conn.createStatement();
		int rs = statement.executeUpdate("DELETE FROM computer WHERE id =" + id);
		if (rs == 1)
		{

			System.out.println(" Computer successfully deleted");

		}
		else
		{ //TODO Error System
			System.out.println("Error while deleting");
		}
		return rs;
	}
	public int addComputer(String name,String introduced,String discontinued, int company_id) 
	{
		int computer_id = 2;
		int rs=0;
		String tname = "NULL";
		String tintroduced = "NULL";
		String tdiscontinued = "NULL";

		Statement statement;
		try {
			statement = conn.createStatement();
			if (name == null)
			{
				tname = "NULL";
			}
			else
			{
				tname = name;
			}
			if (introduced.equals("NULL"))
			{
				tintroduced = "NULL";
			}
			else
			{
				tintroduced = "DATE " + "'"+introduced+"'";
			}
			if (discontinued.equals("NULL"))
			{
				tdiscontinued = "NULL";
			}
			else
			{
				tdiscontinued = "DATE " + "'"+discontinued+"'";;
			}
		    rs = statement.executeUpdate("insert into computer (name,introduced, discontinued, company_id) values('"+tname+"',"+tintroduced+" ,"+tdiscontinued+",(select company.id from company where company.id = "+company_id+"))");
				System.out.println(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 1;
		
	}
	
	
	/**
	 * Method that update a single computer within its Id
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company_id
	 * @param id
	 * @return
	 */
	public int updateComputer(String name,String introduced,String discontinued, int company_id,int id) 
	{
		int computer_id = 2;
		int rs=0;
		String tname = "NULL";
		String tintroduced = "NULL";
		String tdiscontinued = "NULL";

		Statement statement;
		try {
			statement = conn.createStatement();
			if (name == null)
			{
				tname = "NULL";
			}
			else
			{
				tname = name;
			}
			if (introduced.equals("NULL"))
			{
				tintroduced = "NULL";
			}
			else
			{
				tintroduced = "DATE " + "'"+introduced+"'";
			}
			if (discontinued.equals("NULL"))
			{
				tdiscontinued = "NULL";
			}
			else
			{
				tdiscontinued = "DATE " + "'"+discontinued+"'";;
			}
		    rs = statement.executeUpdate("UPDATE computer SET name = '"+tname+"', introduced = "+tintroduced+", discontinued = "+tdiscontinued+", company_id = "+company_id+" WHERE id = " +id);
			//rs = statement.execute("insert into computer (name,introduced, discontinued, company_id) values('"+name+"',NULL ,NULL,"+company_id+")");
				System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
		
	}

	
}
