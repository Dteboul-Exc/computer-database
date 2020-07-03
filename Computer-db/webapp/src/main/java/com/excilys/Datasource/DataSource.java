package com.excilys.Datasource;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.configuration.SpringConfiguration;
import com.zaxxer.hikari.HikariDataSource;



/**
 * Hikari datasource, allowing CDB to access the Database.
 * @author dteboul
 *
 */
public class DataSource {
    private static HikariDataSource ds = SpringConfiguration.getContext().getBean(HikariDataSource.class);

 
    private DataSource() {}
 
    public static synchronized Connection getConn() throws SQLException {
        return ds.getConnection();
    }
	public static void close() throws SQLException
	{
		ds.close();
	}
}
