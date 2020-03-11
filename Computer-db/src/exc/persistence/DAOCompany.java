package exc.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exc.model.Company;

public final class DAOCompany implements DAOCompanyInterface {
	public List<Company> getAllCompany() throws SQLException {
		// TODO Auto-generated method stub
		SQLConnect a =  SQLConnect.getInstance();
		
		List<Company> company = new ArrayList();
		Statement statement = SQLConnect.getConn().createStatement();
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
}
