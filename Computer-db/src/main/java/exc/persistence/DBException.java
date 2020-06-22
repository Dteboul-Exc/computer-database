package main.java.exc.persistence;

import java.sql.SQLException;

import org.springframework.stereotype.Component;



public class DBException extends SQLException{
	DBException(String s)
	{
		super(s);
	}

}
