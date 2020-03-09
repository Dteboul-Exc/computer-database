package exc.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exc.model.Company;

public class SQLConnect implements DAOCompany {
	private Connection conn;
	private Connection connection;
	public void connect( ) throws SQLException
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
		ResultSet rs = statement.executeQuery("select * from company");
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
}
