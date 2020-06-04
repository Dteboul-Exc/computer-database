package test.java;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dbunit.DBTestCase;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import main.java.exc.model.Company;
import main.java.exc.persistence.DAOCompany;
import main.java.exc.service.ServiceDAOCompany;

public class SQLTestingCase extends DatabaseTestCase {
	private DAOCompany DAO = new DAOCompany();
    public static final String TABLE_COMPANY = "company";

    Optional<Company> mCompany = Optional.of( Company.Builder.newInstance().setId(13).setName("End of a century").build());
    
    
    private FlatXmlDataSet loadedDataSet;
	protected IDatabaseConnection getConnection() throws Exception{
		//Class driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/";
		String username = "admincdb";
		String password = "qwerty1234";
		String db = "computer-database-db";
		Connection jdbcConnection = DriverManager.getConnection(url+db, username, password);
		return new DatabaseConnection(jdbcConnection);
	}
	// Load the data which will be inserted for the test
	@SuppressWarnings("deprecation")
	protected IDataSet getDataSet() throws Exception{
		
		loadedDataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("testeddb.xml"));
		return loadedDataSet;
	}
	
	
	@Test
	public void testCkeckDataLoaded() throws DataSetException, SQLException, ParseException {
	       assertNotNull(loadedDataSet);
	       int rowCount = loadedDataSet.getTable(TABLE_COMPANY).getRowCount();
	       Methods m = new Methods();
	       Optional<Company> tested = m.Company_getid(13);
			//Optional<List<Company>> company = Optional.of(new ArrayList());
			
	       assertEquals(true,mCompany.get().equals(tested.get()));

	      
	}

	@Test
	public void successTestFindCompanyById() throws DataSetException, SQLException, ParseException {
	       assertNotNull(loadedDataSet);
	       Optional<Company> tested = DAO.getSpecificCompany(13);
	       assertEquals(mCompany.get().getName(), tested.get().getName());
	}
	
	@Test
	public void failureTestFindCompanyById() throws DataSetException, SQLException, ParseException {
	       assertNotNull(loadedDataSet);
	       Optional<Company> tested = DAO.getSpecificCompany(13);
	       assertEquals(mCompany, tested);
	}
	
	@Test
	public void successTestFindAllCompany() throws DataSetException, SQLException {
			List<Company> list = new ArrayList<Company>();
			list.add(Company.Builder.newInstance().setId(1).setName("Sweden").build());
			list.add(Company.Builder.newInstance().setId(14).setName("Fin de siecle").build());
			list.add(Company.Builder.newInstance().setId(13).setName("End of a century").build());
	       assertNotNull(loadedDataSet);
	       Optional<List<Company>> result = DAO.getAllCompany();
	       assertEquals(Optional.ofNullable(list), result);
	}


}
