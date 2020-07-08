package com.excilys.exception;

import java.sql.SQLException;

/**
 * @author dteboul
 *
 *         Exception class used in the DAO in case of an exception.
 */
public class DBException extends SQLException {
	DBException(String s) {
		super(s);
	}

}
