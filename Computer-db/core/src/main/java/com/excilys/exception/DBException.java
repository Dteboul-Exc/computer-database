package com.excilys.exception;

import java.sql.SQLException;

/**
 * @author dteboul
 *
 *         Exception class used in the DAO in case of an exception.
 */
public class DBException extends SQLException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DBException(String s) {
		super(s);
	}

}
