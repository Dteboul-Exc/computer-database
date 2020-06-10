package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import main.java.exc.model.Company;
import main.java.exc.persistence.DAOCompany;

public class CompanyDAOTesting extends DatabaseTestCase{

	private DAOCompany DAO = new DAOCompany();
    public static final String TABLE_COMPANY = "company";

    Optional<Company> mCompany = Optional.of( Company.Builder.newInstance().setId(13).setName("End of a century").build());
    List<Company> tcompany = new ArrayList();

    Optional<List<Company>> lcompany;
    
    @Before 
    public void teststart()
    {
    	tcompany.add(Company.Builder.newInstance().setId(1).setName("Sweden").build());
    	tcompany.add(Company.Builder.newInstance().setId(14).setName("Findesiecle").build());
    	tcompany.add(Company.Builder.newInstance().setId(13).setName("End of a century").build());
    	
    	
    	lcompany = Optional.ofNullable(tcompany);
    }
    protected DatabaseOperation getSetUpOperation() throws Exception
    {
    	tcompany.add(Company.Builder.newInstance().setId(1).setName("Sweden").build());
    	tcompany.add(Company.Builder.newInstance().setId(14).setName("Findesiecle").build());
    	tcompany.add(Company.Builder.newInstance().setId(13).setName("End of a century").build());
    	lcompany = Optional.ofNullable(tcompany);
        return DatabaseOperation.REFRESH;
    }
    
    
    
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
	@Test
	public void testCkeckDataLoaded() throws DataSetException, SQLException, ParseException {
	       assertNotNull(loadedDataSet);
	       int rowCount = loadedDataSet.getTable(TABLE_COMPANY).getRowCount();
			//Optional<List<Company>> company = Optional.of(new ArrayList());
			
	       assertEquals(3,rowCount);

	      
	}

	@Test
	public void testsuccessGetAllCompany() throws DataSetException, SQLException, ParseException {
		System.out.print("dz");
		//System.out.println(DAO.getAllCompany().get().toString());
	///tcompany.removeAll(DAO.getAllCompany().get());
	       //assertEquals(0,tcompany.size());
    System.out.println(tcompany.equals(lcompany.get()));
	//JUnit4.assertEquals(tcompany.toString(),DAO.getAllCompany().get().toString());
	       
	}
	@Test
	public void test3() {
		assertEquals(true,true);
	}
	// Load the data which will be inserted for the test
	@SuppressWarnings("deprecation")
	protected IDataSet getDataSet() throws Exception{
		
		loadedDataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("testeddb.xml"));
		return loadedDataSet;
	}

}