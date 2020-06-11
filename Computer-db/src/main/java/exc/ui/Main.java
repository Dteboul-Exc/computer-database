package main.java.exc.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import main.java.exc.ui.MainMenu;

public class Main {

	public static void main(String[] args) throws IOException, SQLException, ParseException, ClassNotFoundException {
		MainMenu.menu();
	}
}
