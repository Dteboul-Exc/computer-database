package main.java.exc.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import main.java.exc.spring.SpringConfiguration;



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
