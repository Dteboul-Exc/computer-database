package com.excilys.exception;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

  

/**
 * @author dteboul
 *
 *Exception class used in the DAO in case of an exception.
 */
public class DBException extends SQLException{
	DBException(String s)
	{
		super(s);
	}

}
