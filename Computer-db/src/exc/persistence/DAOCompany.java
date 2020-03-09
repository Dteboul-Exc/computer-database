package exc.persistence;

import java.sql.SQLException;
import java.util.List;

import exc.model.Company;

public interface DAOCompany {
	public List<Company> getAllCompany() throws SQLException;

}
