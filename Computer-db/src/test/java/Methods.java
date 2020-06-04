package test.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import main.java.exc.model.Company;

public class Methods {
	private Connection getConnection() throws SQLException 
	{
		String url = "jdbc:mysql://localhost:3306/";
		String username = "admincdb";
		String password = "qwerty1234";
		String db = "computer-database-db";
		return  DriverManager.getConnection(url+db, username, password);
	}
	public Optional<Company>  Company_getid(int id) throws SQLException
	{
		Company company = Company.Builder.newInstance().build();
		
		Statement statement = getConnection().createStatement();
		ResultSet resset = statement.executeQuery("select * from company where id = " + id);
		if (resset.next())
		{
			System.out.print(resset);


				int company_id = resset.getInt("id");
				String name = resset.getString("name");
				company.setId(company_id);
				company.setName(name);

				
		}
		else
		{
			company = null;
		}
		//System.out.println("id = " + company.getId());
		return Optional.ofNullable(company);
	}

}
