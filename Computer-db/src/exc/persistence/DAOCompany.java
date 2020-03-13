package exc.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exc.model.Company;

public class DAOCompany {
	/**
	 * Return the list of all companies present in the SQL database. Will throw SQLException in case it cannot access the database
	 * 
	 * 
	 *@return The list of all the company
	 *@throws SQLException
	 */
	public static Optional<List<Company>> getAllCompany() throws SQLException {
		Connection conn = SQLConnect.getConn();
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
}
