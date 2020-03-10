package exc.persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import exc.model.Company;
import exc.model.Computer;

public interface DAOComputer {
	public List<Computer> getAllComputer() throws SQLException;
	public <Optionnal>Computer getSpecificComputer(int id) throws SQLException;
	public int AddComputer(String introduced,String discontinued,String name,int company_id) throws SQLException;
	

}
