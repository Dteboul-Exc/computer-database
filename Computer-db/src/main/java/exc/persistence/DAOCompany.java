package main.java.exc.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.exc.model.Company;
import main.java.exc.ui.MainMenu;

public class DAOCompany {
	/**
	 * Return the list of all companies present in the SQL database. Will throw SQLException in case it cannot access the database
	 * 
	 * 
	 *@return The list of all the company
	 *@throws SQLException
	 */
	public static Optional<List<Company>> getAllCompany() throws SQLException {
		BasicConfigurator.configure();
		Logger logger = LoggerFactory.getLogger(DAOCompany.class);
	    logger.debug("getAllCompany start");
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
