package main.java.exc.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQLConnect {
	private static SQLConnect instance = new SQLConnect();
	private static Connection conn;

	public static Connection getConn() {
		return conn;
	}

	private Connection connection;

	public static SQLConnect getInstance() {
		return instance;
	}

	private SQLConnect() {
	}

	/**
	 * Method that allow a connection to the SQL Database
	 * 
	 * @throws SQLException
	 */

	public void close() throws SQLException {
		conn.close();
	}

	public void connect() throws SQLException, ClassNotFoundException {
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		String url = "jdbc:mysql://localhost:3306/";
		String username = "admincdb";
		String password = "qwerty1234";
		String db = "computer-database-db";
		conn = DriverManager.getConnection(url + db, username, password);
		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection established successfully!");
		} catch (SQLException e) {
			throw new IllegalStateException("Unable to connect to the database. " + e.getMessage());
		}
	}

}
