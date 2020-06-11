package main.java.exc.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.exc.model.Company;

public class DAOCompany {
	/**
	 * Return the list of all companies present in the SQL database. Will throw SQLException in case it cannot access the database
	 * 
	 * 
	 *@return The list of all the company
	 *@throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public Optional<List<Company>> getAllCompany() throws SQLException{

		Connection conn = DataSource.getConn();
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
        conn.close();
        Optional<List<Company>> result = Optional.ofNullable(company);
		return result;
	}
	public Optional<Company> getSpecificCompany(int id) throws SQLException, ParseException {
		if (id == 0) 
			return Optional.empty();
		Connection conn = DataSource.getConn();
		Company company = Company.Builder.newInstance().build();
		Statement statement = conn.createStatement();
		ResultSet resset = statement.executeQuery("select computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id where computer.id =" + id);
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
}
