package exc.ui;

import java.text.ParseException;
import java.util.Scanner;

import exc.mapper.DateMapper;
import exc.model.Company;
import exc.model.Computer;
import exc.persistence.DAOComputer;
import exc.persistence.SQLConnect;
import exc.service.Computermapper;

public class SecondaryMenus {
	/**
	 * Method that allow a computer to modify a computer in the database. The argument is the computer taken from the Database.
	 * The menu has been done in such a way that it allows the user to change values if he isn't satisfied 
	 * 
	 * @param computer
	 * @return
	 * @throws ParseException
	 */
	public static int Computer_Modify(Computer computer) throws ParseException
	{

		Scanner reader = new Scanner(System.in);
		Computermapper sta;
		boolean check = true;
		System.out.println("Default Parameters");
		System.out.println(" name : " +computer.getName() +" start :" + computer.getIntroduced() + " end :" + computer.getDiscontinued() + " C_id " + computer.getCompany().getId());
		sta = Computermapper.Main;
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
					sta = Computermapper.Name; 
				}
				else if (select == 2)
				{
					sta = Computermapper.Introduced; 
				}
				else if (select == 3)
				{
					sta = Computermapper.Discontinued;					
				}
				else if (select==4)
				{
					sta =Computermapper.Company_id;
				}
				else if (select == 5)
				{
					sta = Computermapper.Validate;
				}
				else if (select == 6)
				{
					sta = Computermapper.End;
				}
				break;
			case Name:
				sta = Computermapper.Main;
				System.out.println("Enter the name of the Computer");
				String name = reader.next();
				computer.setName(name);
				break;
			case Introduced:
				sta = Computermapper.Main;
				System.out.println("Enter the introduced (format YYYY-MM-DD)");
				String introduced = reader.next();
				computer.setIntroduced(DateMapper.StringConverter(introduced).get());
				break;
			case Discontinued:
				sta = Computermapper.Main;
				System.out.println("Enter the discontinued (format YYYY-MM-DD)");
				String discontinued = reader.next();
				computer.setDiscontinued(DateMapper.StringConverter(discontinued).get());
				break;
			case Company_id:
				sta = Computermapper.Main;
				System.out.println("Enter the company id ");
				int id1 = reader.nextInt();
				Company comp =  Company.Builder.newInstance().setId(id1).build();
				computer.setCompany(comp);
				break;
			case Validate:
				sta = Computermapper.Main;
				System.out.println(" name : " +computer.getName() +" start :" + computer.getIntroduced() + " end :" + computer.getDiscontinued() + " C_id " + computer.getCompany().getId());
				break;
			case End:
				SQLConnect a =  SQLConnect.getInstance();
				System.out.println(" name : " +computer.getName() +" start :" + computer.getIntroduced() + " end :" + computer.getDiscontinued() + " C_id " + computer.getCompany().getId());
				DAOComputer.updateComputer(computer.getName(),DateMapper.DateConverter(computer.getIntroduced()).get(),DateMapper.DateConverter(computer.getDiscontinued()).get(), computer.getCompany().getId(),computer.getId());
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
	 */
	public static void Computer_Add() throws ParseException
	{
		Computer computer = Computer.Builder.newInstance().build();
		computer.setName(null);
		Company comp =  Company.Builder.newInstance().setId(0).build();
		computer.setCompany(comp);
		computer.setDiscontinued(null);
		computer.setIntroduced(null);
		Scanner reader = new Scanner(System.in);
		Computermapper sta;
		boolean check = true;
		sta = Computermapper.Main;
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
					sta = Computermapper.Name; 
				}
				else if (select == 2)
				{
					sta = Computermapper.Introduced; 
				}
				else if (select == 3)
				{
					sta = Computermapper.Discontinued;					
				}
				else if (select==4)
				{
					sta =Computermapper.Company_id;
				}
				else if (select == 5)
				{
					sta = Computermapper.Validate;
				}
				else if (select == 6)
				{
					sta = Computermapper.End;
				}
				break;
			case Name:
				sta = Computermapper.Main;
				System.out.println("Enter the name of the Computer");
				String name = reader.next();
				computer.setName(name);
				break;
			case Introduced:
				sta = Computermapper.Main;
				System.out.println("Enter the introduced (format YYYY-MM-DD)");
				String introduced = reader.next();
				computer.setIntroduced(DateMapper.StringConverterInput(introduced).get());
				break;
			case Discontinued:
				sta = Computermapper.Main;
				System.out.println("Enter the discontinued (format YYYYMM-DD)");
				String discontinued = reader.next();
				computer.setDiscontinued(DateMapper.StringConverterInput(discontinued).get());
				break;
			case Company_id:
				sta = Computermapper.Main;
				System.out.println("Enter the company id ");
				int id = reader.nextInt();
				comp =  Company.Builder.newInstance().setId(id).build();
				computer.setCompany(comp);
				break;
			case Validate:
				sta = Computermapper.Main;
				System.out.println(" name : " +computer.getName() +" start :" + computer.getIntroduced() + " end :" + computer.getDiscontinued() + " C_id " + computer.getCompany().getId());
				break;
			case End:
				SQLConnect a =  SQLConnect.getInstance();
				System.out.println("babarhum");
				System.out.println(" name : " +computer.getName() +" start :" + computer.getIntroduced() + " end :" + computer.getDiscontinued() + " C_id " + computer.getCompany().getId());
				DAOComputer.addComputer(computer.getName(),DateMapper.DateConverter(computer.getIntroduced()).get(),DateMapper.DateConverter(computer.getDiscontinued()).get(), computer.getCompany().getId());
				check= false;
				return;
			}
		}
	}

}
