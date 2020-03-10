package exc.ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
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
				Optional<Computer> compute = a.getSpecificComputer(s);
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


				break;
			case Exit:
				break;
			default:
				break;
			}
		}
	}
	private void Computer_Add()
	{
		Computermapper sta;
		sta = Computermapper.Main;
		while (true) {
			switch (sta) {
			case Main:
				Scanner reader = new Scanner(System.in);
				System.out.println("Setup the Creation of the PC");
				System.out.println("1 - name [Mandatory]");
				System.out.println("2 - introduced ");
				System.out.println("3 - discontinued ");
				System.out.println("4 - company[set id] ");
				System.out.println("5 - Validate ");
				System.out.println("6 - Exit ");
				int select = reader.nextInt();
				break;
			case Name:
				break;
			case introduced:
				break;
			case discontinued:
				break;
			case Company_id:
				break;
			case Validate:
				break;
			}
		}
	}
	

}
