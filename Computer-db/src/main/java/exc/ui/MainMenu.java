package main.java.exc.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


import org.slf4j.LoggerFactory;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;


import main.java.exc.mapper.DateMapper;
import main.java.exc.model.Company;
import main.java.exc.model.Computer;
import main.java.exc.persistence.DAOCompany;
import main.java.exc.persistence.DAOComputer;
import main.java.exc.persistence.SQLConnect;
import main.java.exc.service.Page;
import main.java.exc.service.ServiceDAOCompany;
import main.java.exc.service.ServiceDAOComputer;
import main.java.exc.service.state;
import main.java.exc.ui.SecondaryMenus;


public class MainMenu {
	
	/**
	 * Method Allowing an user to delete,create or modify a computer. Show a list of the computers or companies present in the DB. Also allow an user 
	 * to have the details of a specific computer. To use the Menu, the first numeric value in the user input is read.
	 * 
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static void menu() throws SQLException, ParseException
	{
		BasicConfigurator.configure();
		Logger logger = LoggerFactory.getLogger(MainMenu.class);
	    logger.debug("Main Menu Initialized");

		state sta;
		sta = state.Main;
		Scanner reader = new Scanner(System.in);
		Optional<Computer> compute;
		SQLConnect a =  SQLConnect.getInstance();
		a.connect();

		while(true)
		{
			switch (sta) {
			case Main:
				System.out.println("1  List Computer");
				System.out.println("2  List Company");
				System.out.println("3  Show Computer detail");
				System.out.println("4  Add Computer");
				System.out.println("5  Modify Computer");
				System.out.println("6  Remove Computer");
				System.out.println("7  Exit \n");
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				
				int s = reader.nextInt();
				//reader.close();
				System.out.println(s);
				if (s == 1)
				{
					sta = state.LComputer; 
				}
				else if (s == 2)
				{
					sta = state.LCompany; 
				}
				else if (s== 3)
				{
					sta = state.DComputer; 					
				}
				else if (s==4)
				{
					sta =state.CreateComputer;
				}
				else if (s==5)
				{
					sta = state.UpdateComputer;
				}
				else if (s==6)
				{
					sta = state.DeleteComputer;
				}
				else if (s==7)
				{
					sta = state.Exit;
				}
				logger.debug("user is going to the menu " + sta);
				break;
			case LComputer :
				sta = state.Main;
				Optional<List<Computer>> optcomputer = ServiceDAOComputer.getAllComputer();
				
				if (!optcomputer.isEmpty())
				{
					logger.debug("user is being shown a list of the computer in the database");
					List<Computer> lcomp = optcomputer.get();
					Page h = new Page();
					h.Following(lcomp);
				}
				break;
			case LCompany :
				sta = state.Main;
				Optional<List<Company>> optcompany = ServiceDAOCompany.getAllCompany();
				if (!optcompany.isEmpty())
				{
					logger.debug("user is being shown a list of the companies in the database");
					List<Company> lcny = optcompany.get();
					lcny.forEach((i)->System.out.println("id : "+i.getId()+" name : " +i.getName()));
				}
				else
				{
					System.out.println("Error, list company is empty");
					logger.error("no computer was shown in the database");
				}
				break;
			case DComputer :
				logger.debug("User search  a specific computer by id");
				sta = state.Main;
				System.out.println("Enter the id of the computer you want to select");
				s = reader.nextInt();
				compute = ServiceDAOComputer.getSpecificComputer(s);
				if (compute.isPresent())
				{	 
					Computer c = compute.get();
					System.out.println(" id : "+c.getId()+" name : " +c.getName() +" start : " + c.getIntroduced() + " end :" + c.getDiscontinued() +  " | Company :  " + c.getCompany().getName());
				}
				else
				{
					System.out.println("pc not found");
					logger.error("the computer with id "+ s+"was not found ");
				}
				break;
			case CreateComputer:
				sta = state.Main;
				logger.debug("User tries to create a computer");
				try {
					SecondaryMenus.Computer_Add();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					logger.error("Parse exception while trying to create a computer: " + e);
					e.printStackTrace();
				}

				break;
			case UpdateComputer:
				logger.debug("User tries to update a specific computer by id");
				sta = state.Main;
				System.out.println("Enter the id of the computer you want to select");
				s = reader.nextInt();
				try {
					compute = DAOComputer.getSpecificComputer(s);
					if (compute.isPresent())
					{	 
						Computer c = compute.get();
						try {
							System.out.println(SecondaryMenus.Computer_Modify(c));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							logger.error("Parse exception while trying to update a computer: " + e);
							e.printStackTrace();
						}
					}
					else
					{
						logger.error("PC was not found by id  : " + s);
						System.out.println("pc not found");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					logger.error("exception while trying to update a computer: " + e1);
					e1.printStackTrace();
				}
				break;
			case DeleteComputer:
				logger.debug("User tries to delete a specific computer by id");
				sta = state.Main;
				System.out.println("Enter the id of the computer you want to delete");
				s = reader.nextInt();
				ServiceDAOComputer.deleteSpecificComputer(s);
				break;
			case Exit:
				System.exit(0); 
				break;
			default:
				break;
			}
		}
	
	}

}
