package main.java.exc.persistence;

import java.sql.SQLException;

public class DBException extends SQLException {
	DBException(String s) {
		super(s);
	}

}
