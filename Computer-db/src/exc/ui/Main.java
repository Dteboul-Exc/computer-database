package exc.ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import exc.mapper.*;
import exc.model.Company;
import exc.model.Computer;
import exc.persistence.SQLConnect;
public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
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
				break;
			case LComputer :
				sta = state.Main;
				List<Computer> lcomp = a.getAllComputer();
				lcomp.forEach((i)->System.out.println("id : "+i.getId()+" name : " +i.getName() +" start :" + i.getIntroduced() + " end :" + i.getDiscontinued() + " C_id " + i.getCompany_id() ));
				break;
			case LCompany :
				sta = state.Main;
				List<Company> lcny = a.getAllCompany();
				lcny.forEach((i)->System.out.println("id : "+i.getId()+" name : " +i.getName()));
				break;
			case DComputer :
				sta = state.Main;
				System.out.println("Enter the id of the computer you want to select");
				s = reader.nextInt();
				compute = a.getSpecificComputer(s);
				if (compute.isPresent())
				{	 
					Computer c = compute.get();
					System.out.println("id : "+c.getId()+" name : " +c.getName() +" start :" + c.getIntroduced() + " end :" + c.getDiscontinued() + " C_id " + c.getCompany_id());
				}
				else
				{
					System.out.println("pc not found");
				}
				break;
			case CreateComputer:
				try {
					Computer_Add();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case UpdateComputer:
				sta = state.Main;
				System.out.println("Enter the id of the computer you want to select");
				s = reader.nextInt();
				compute = a.getSpecificComputer(s);
				if (compute.isPresent())
				{	 
					Computer c = compute.get();
					try {
						System.out.println(Computer_Modify(c));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("pc not found");
				}
				break;
			case DeleteComputer:
				sta = state.Main;
				System.out.println("Enter the id of the computer you want to delete");
				s = reader.nextInt();
				a.deleteSpecificComputer(s);
				break;
			case Exit:
				System.exit(0); 
				break;
			default:
				break;
			}
		}
	}

	
	private static int Computer_Modify(Computer computer) throws ParseException
	{

		Scanner reader = new Scanner(System.in);
		Computermapper sta;
		boolean check = true;
		System.out.println("Default Parameters");
		System.out.println(" name : " +computer.getName() +" start :" + computer.getIntroduced() + " end :" + computer.getDiscontinued() + " C_id " + computer.getCompany_id());
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
				computer.setIntroduced(introduced);
				break;
			case Discontinued:
				sta = Computermapper.Main;
				System.out.println("Enter the discontinued (format YYYY-MM-DD)");
				String discontinued = reader.next();
				computer.setDiscontinued(discontinued);
				break;
			case Company_id:
				sta = Computermapper.Main;
				System.out.println("Enter the company id ");
				int id1 = reader.nextInt();
				computer.setCompany_id(id1);
				break;
			case Validate:
				sta = Computermapper.Main;
				System.out.println(" name : " +computer.getName() +" start :" + computer.getIntroduced() + " end :" + computer.getDiscontinued() + " C_id " + computer.getCompany_id());
				break;
			case End:
				SQLConnect a =  SQLConnect.getInstance();
				System.out.println(" name : " +computer.getName() +" start :" + computer.getIntroduced() + " end :" + computer.getDiscontinued() + " C_id " + computer.getCompany_id());
				a.updateComputer(computer.getName(),computer.getIntroduced(),computer.getDiscontinued(), computer.getCompany_id(),computer.getId());
				check= false;
				return 0;
			}
		}
		return 0;
	}
	private static int Computer_Add() throws ParseException
	{
		Computer computer = new Computer();
		computer.setName(null);
		computer.setCompany_id(0);
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
				computer.setIntroduced(introduced);
				break;
			case Discontinued:
				sta = Computermapper.Main;
				System.out.println("Enter the discontinued (format YYYY-MM-DD)");
				String discontinued = reader.next();
				computer.setDiscontinued(discontinued);
				break;
			case Company_id:
				sta = Computermapper.Main;
				System.out.println("Enter the company id ");
				int id = reader.nextInt();
				computer.setCompany_id(id);
				break;
			case Validate:
				sta = Computermapper.Main;
				System.out.println(" name : " +computer.getName() +" start :" + computer.getIntroduced() + " end :" + computer.getDiscontinued() + " C_id " + computer.getCompany_id());
				break;
			case End:
				SQLConnect a =  SQLConnect.getInstance();
				System.out.println("babarhum");
				System.out.println(" name : " +computer.getName() +" start :" + computer.getIntroduced() + " end :" + computer.getDiscontinued() + " C_id " + computer.getCompany_id());
				a.addComputer(computer.getName(),computer.getIntroduced(),computer.getDiscontinued(), computer.getCompany_id());
				check= false;
				return 0;
			}
		}
		return 0;
	}
	

}
