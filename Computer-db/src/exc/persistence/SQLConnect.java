package exc.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exc.model.Company;
import exc.model.Computer;

public  final class SQLConnect implements DAOCompany {
	private static SQLConnect instance = new SQLConnect();
	private   Connection conn;
	private   Connection connection;

	public static SQLConnect getInstance() {
		return instance;
	}

	private SQLConnect() {}
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
        // if the connection couldn't be established raise an exception
        catch (SQLException e) {
        	throw new IllegalStateException("Unable to connect to the database. " + e.getMessage());
        }
	}

	@Override
	public List<Company> getAllCompany() throws SQLException {
		// TODO Auto-generated method stub
		List<Company> company = new ArrayList();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select name,id from company");
        while ( rs.next() ) {
        	Company tcompany = new Company();
            String name = rs.getString("name");
            int id = rs.getInt("id");
            tcompany.setId(id);
            tcompany.setName(name);
            company.add(tcompany);
            
        }
		return company;
	}
	public List<Computer> getAllComputer() throws SQLException{
		List<Computer> computer = new ArrayList();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select computer.name,company.name as company ,computer.id,introduced,discontinued,company_id from computer LEFT JOIN company ON computer.company_id = company.id");
        while ( rs.next() ) {
        	Computer tcomputer = new Computer();
            String name = rs.getString("name");
            String company = rs.getString("company");
            int id = rs.getInt("id");
            String introduced = rs.getString("introduced");
            String discontinued = rs.getString("discontinued");
            int company_id = rs.getInt("company_id");
            tcomputer.setId(id);
            tcomputer.setName(name);
            tcomputer.setIntroduced(introduced);
            tcomputer.setDiscontinued(discontinued);
            tcomputer.setCompany_id(company_id);
            tcomputer.setCompany_name(company);
            computer.add(tcomputer);
            
        }
		return computer;
	}
	public Optional<Computer> getSpecificComputer(int id) throws SQLException {
		Computer computer = new Computer();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from computer where id =" + id);
		if (rs.next())
		{

				String name = rs.getString("name");
				int id1 = rs.getInt("id");
				String introduced = rs.getString("introduced");
				String discontinued = rs.getString("discontinued");
				int company_id = rs.getInt("company_id");
				computer.setId(id);
				computer.setName(name);
				computer.setIntroduced(introduced);
				computer.setDiscontinued(discontinued);
				computer.setCompany_id(company_id);

		}
		else
		{
			computer = null;
		}
		System.out.println("id = " + computer.getId());
		return Optional.ofNullable(computer);
	}
	public int addComputer(String name,String introduced,String discontinued, int company_id) 
	{
		int computer_id = 2;
		boolean rs;
		Statement statement;
		try {
			statement = conn.createStatement();
			 //rs = statement.execute("insert into computer (name,introduced, discontinued, company_id) values(" + name +",NULL ,NULL,(select company.id from company where company.id ="+ company_id+"))");
				// rs = statement.executeUpdate("insert into computer (name,introduced, discontinued, company_id) values(" + name +",DATE "+introduced+" ,DATE "+discontinued+",(select company.id from company where company.id ="+ company_id+"))");
				
				rs = statement.execute("insert into computer (name,introduced, discontinued, company_id) values('"+name+"',NULL ,NULL,(select company.id from company where company.id = "+company_id+"))");
			//rs = statement.execute("insert into computer (name,introduced, discontinued, company_id) values('"+name+"',NULL ,NULL,"+company_id+")");
				System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
		
	}

	
}
