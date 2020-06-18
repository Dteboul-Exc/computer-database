package main.java.exc.ui;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import main.java.exc.mapper.CompanyMapper;
import main.java.exc.mapper.DateMapper;
import main.java.exc.model.Company;
import main.java.exc.model.CompanyDTO;
import main.java.exc.model.Computer;
import main.java.exc.model.ComputerDTO;
import main.java.exc.persistence.DAOComputer;
import main.java.exc.persistence.SQLConnect;
import main.java.exc.ui.MenuComputerInterface;
import main.java.exc.service.ServiceComputer;

public class SecondaryMenus {
	/**
	 * Method that allow a computer to modify a computer in the database. The argument is the computer taken from the Database.
	 * The menu has been done in such a way that it allows the user to change values if he isn't satisfied 
	 * 
	 * @param c
	 * @return
	 * @throws ParseException
	 * @throws ClassNotFoundException 
	 * @throws NumberFormatException 
	 */
	public static int Computer_Modify(ComputerDTO c) throws ParseException, NumberFormatException, ClassNotFoundException
	{
		ServiceComputer DAOComputer =new ServiceComputer();

		Scanner reader = new Scanner(System.in);
		MenuComputerInterface sta;
		boolean check = true;
		System.out.println("Default Parameters");
		System.out.println(" name : " +c.getName() +" start :" + c.getIntroduced() + " end :" + c.getDiscontinued() + " C_id " + c.getCompany());
		sta = MenuComputerInterface.Main;
		while (check) {
			switch (sta) {
			case Main:
				System.out.println("Setup the Modification of the PC");
				System.out.println("1 - name ");
				System.out.println("2 - introduced ");
				System.out.println("3 - discontinued ");
				System.out.println("4 - company[set id] ");
				System.out.println("5 - Validate ");
				System.out.println("6 - Exit ");
				int select = reader.nextInt();
				if (select == 1)
				{
					sta = MenuComputerInterface.Name; 
				}
				else if (select == 2)
				{
					sta = MenuComputerInterface.Introduced; 
				}
				else if (select == 3)
				{
					sta = MenuComputerInterface.Discontinued;					
				}
				else if (select==4)
				{
					sta =MenuComputerInterface.Company_id;
				}
				else if (select == 5)
				{
					sta = MenuComputerInterface.Validate;
				}
				else if (select == 6)
				{
					sta = MenuComputerInterface.End;
				}
				break;
			case Name:
				sta = MenuComputerInterface.Main;
				System.out.println("Enter the name of the Computer");
				String name = reader.next();
				c.setName(name);
				break;
			case Introduced:
				sta = MenuComputerInterface.Main;
				System.out.println("Enter the introduced (format YYYY-MM-DD)");
				String introduced = reader.next();
				c.setIntroduced(introduced);
				break;
			case Discontinued:
				sta = MenuComputerInterface.Main;
				System.out.println("Enter the discontinued (format YYYY-MM-DD)");
				String discontinued = reader.next();
				c.setDiscontinued(discontinued);
				break;
			case Company_id:
				sta = MenuComputerInterface.Main;
				System.out.println("Enter the company id ");
				String id1 = reader.next();
				CompanyDTO comp =  new CompanyDTO();
				comp.setId(id1);
				c.setCompany(comp);
				break;
			case Validate:
				sta = MenuComputerInterface.Main;
				System.out.println(" name : " +c.getName() +" start :" + c.getIntroduced() + " end :" + c.getDiscontinued() + " C_id " + c.getCompany());
				break;
			case End:
				SQLConnect a =  SQLConnect.getInstance();
				System.out.println(" name : " +c.getName() +" start :" + c.getIntroduced() + " end :" + c.getDiscontinued() + " C_id " + c.getCompany());
				
				DAOComputer.updateComputer(c);
				check= false;
				return 0;
			}
		}
		return 0;
	}
	/**
	 * Method that allow a computer to create a computer in the database. 
	 * The menu has been done in such a way that it allows the user to change values if he isn't satisfied 
	 * 
	 * @throws ParseException
	 * @throws ClassNotFoundException 
	 */
	public static void Computer_Add() throws ParseException, ClassNotFoundException
	{
		Computer computer = Computer.Builder.newInstance().build();
		computer.setName(null);
		Company comp =  Company.Builder.newInstance().setId(0).build();
		computer.setCompany(comp);
		computer.setDiscontinued(null);
		computer.setIntroduced(null);
		Scanner reader = new Scanner(System.in);
		MenuComputerInterface sta;
		boolean check = true;
		sta = MenuComputerInterface.Main;
		while (check) {
			switch (sta) {
			case Main:
				System.out.println("Setup the Creation of the PC");
				System.out.println("1 - name ");
				System.out.println("2 - introduced ");
				System.out.println("3 - discontinued ");
				System.out.println("4 - company[set id] ");
				System.out.println("5 - Validate ");
				System.out.println("6 - Exit ");
				int select = reader.nextInt();
				if (select == 1)
				{
					sta = MenuComputerInterface.Name; 
				}
				else if (select == 2)
				{
					sta = MenuComputerInterface.Introduced; 
				}
				else if (select == 3)
				{
					sta = MenuComputerInterface.Discontinued;					
				}
				else if (select==4)
				{
					sta =MenuComputerInterface.Company_id;
				}
				else if (select == 5)
				{
					sta = MenuComputerInterface.Validate;
				}
				else if (select == 6)
				{
					sta = MenuComputerInterface.End;
				}
				break;
			case Name:
				sta = MenuComputerInterface.Main;
				System.out.println("Enter the name of the Computer");
				String name = reader.next();
				computer.setName(name);
				break;
			case Introduced:
				sta = MenuComputerInterface.Main;
				System.out.println("Enter the introduced (format YYYY-MM-DD)");
				String introduced = reader.next();
				computer.setIntroduced(DateMapper.StringConverterInput(introduced).get());
				break;
			case Discontinued:
				sta = MenuComputerInterface.Main;
				System.out.println("Enter the discontinued (format YYYYMM-DD)");
				String discontinued = reader.next();
				computer.setDiscontinued(DateMapper.StringConverterInput(discontinued).get());
				break;
			case Company_id:
				sta = MenuComputerInterface.Main;
				System.out.println("Enter the company id ");
				int id = reader.nextInt();
				comp =  Company.Builder.newInstance().setId(id).build();
				computer.setCompany(comp);
				break;
			case Validate:
				sta = MenuComputerInterface.Main;
				System.out.println(" name : " +computer.getName() +" start :" + computer.getIntroduced() + " end :" + computer.getDiscontinued() + " C_id " + computer.getCompany().getId());
				break;
			case End:
				SQLConnect a =  SQLConnect.getInstance();
				System.out.println(" name : " +computer.getName() +" start :" + computer.getIntroduced() + " end :" + computer.getDiscontinued() + " C_id " + computer.getCompany().getId());
				try {
					DAOComputer add = new DAOComputer();
					add.addComputer(computer.getName(),DateMapper.DateConverter(computer.getIntroduced()).get(),DateMapper.DateConverter(computer.getDiscontinued()).get(), computer.getCompany().getId());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				check= false;
				return;
			}
		}
	}

}
