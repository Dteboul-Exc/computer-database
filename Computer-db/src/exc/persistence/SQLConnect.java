package exc.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exc.mapper.DateMapper;
import exc.model.Company;
import exc.model.Computer;

public  final class SQLConnect implements DAOCompanyInterface {
	private static SQLConnect instance = new SQLConnect();
	private static   Connection conn;
	public  static Connection getConn() {
		return conn;
	}

	private   Connection connection;

	public static SQLConnect getInstance() {
		return instance;
	}

	private SQLConnect() {}
	/**
	 * Method that allow a connection to the SQL Database
	 * @throws SQLException
	 */
	public  void  connect() throws SQLException
	{
		String url = "jdbc:mysql://localhost:3306/";
		String username = "admincdb";
		String password = "qwerty1234";
		String db = "computer-database-db";
		conn = DriverManager.getConnection(url+db, username, password);
        try {
        	connection = DriverManager.getConnection(url, username, password);
        	System.out.println("Connection established successfully!");
        }
        catch (SQLException e) {
        	throw new IllegalStateException("Unable to connect to the database. " + e.getMessage());
        }
	}


	
}
