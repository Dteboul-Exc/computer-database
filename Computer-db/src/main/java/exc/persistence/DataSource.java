package main.java.exc.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
 
    static {
    	config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl( "jdbc:mysql://localhost:3306/computer-database-db" );
        config.setUsername( "admincdb" );
        config.setPassword( "qwerty1234" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        config.addDataSourceProperty("maximumPoolSize", "1");
        config.addDataSourceProperty("minimumIdle", "1");
        ds = new HikariDataSource( config );
    }
 
    private DataSource() {}
 
    public static Connection getConn() throws SQLException {
        return ds.getConnection();
    }
	public void close() throws SQLException
	{
		ds.close();
	}
}
