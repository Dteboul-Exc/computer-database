package exc.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import exc.mapper.DateMapper;
import exc.model.Company;
import exc.model.Computer;
import exc.persistence.SQLConnect;
import exc.service.state;
import exc.ui.SecondaryMenus;


public class MainMenu {
	public static void menu() throws SQLException, ParseException
	{
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
				lcomp.forEach((i)->{
					try {
						System.out.println("id : "+i.getId()+" name : " +i.getName() +" |start : " + DateMapper.DateConverter(i.getIntroduced()) + " end : " + DateMapper.DateConverter(i.getDiscontinued()) + " | Company :  " + i.getCompany().getName() );
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				});
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
					System.out.println(" id : "+c.getId()+" name : " +c.getName() +" start : " + c.getIntroduced() + " end :" + c.getDiscontinued() + " | Company :  " + c.getCompany().getName());
				}
				else
				{
					System.out.println("pc not found");
				}
				break;
			case CreateComputer:
				sta = state.Main;
				try {
					SecondaryMenus.Computer_Add();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case UpdateComputer:
				sta = state.Main;
				System.out.println("Enter the id of the computer you want to select");
				s = reader.nextInt();
				try {
					compute = a.getSpecificComputer(s);
					if (compute.isPresent())
					{	 
						Computer c = compute.get();
						try {
							System.out.println(SecondaryMenus.Computer_Modify(c));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
					{
						System.out.println("pc not found");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case DeleteComputer:
				sta = state.Main;
				System.out.println("Enter the id of the computer you want to delete");
				s = reader.nextInt();
				try {
					a.deleteSpecificComputer(s);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
