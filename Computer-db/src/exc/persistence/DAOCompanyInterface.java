package exc.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import exc.model.Company;

public interface DAOCompanyInterface {
	public Optional<List<Company>> getAllCompany() throws SQLException;

}
