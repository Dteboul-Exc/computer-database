package test.java;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.DBTestCase;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Test;

public class SQLTestingCase extends DatabaseTestCase {
    public static final String TABLE_COMPANY = "company";
    private static final String DBUNIT_DATA_DIR = "/test/resources/";
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
		loadedDataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream(DBUNIT_DATA_DIR+"testeddb.xml"));
		return loadedDataSet;
	}
	
	
	@Test
	public void testCkeckDataLoaded() throws DataSetException {
	       assertNotNull(loadedDataSet);
	       int rowCount = loadedDataSet.getTable(TABLE_COMPANY).getRowCount();
	       assertEquals(3, rowCount);
	}



}
