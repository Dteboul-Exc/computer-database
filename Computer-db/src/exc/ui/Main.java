package exc.ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import exc.mapper.*;
import exc.model.Company;
import exc.persistence.SQLConnect;
public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		state sta;
		sta = state.Main;
		SQLConnect a = new SQLConnect();
		try {
			a.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true)
		{
			switch (sta) {
			case Main:
				System.out.println("1  List Computer \n");
				System.out.println("2  List Company \n");
				System.out.println("3  Add Computer \n");
				System.out.println("4  Modify Computer \n");
				System.out.println("5  Remove Computer \n");
				System.out.println("6  Exit \n");
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				String s = bufferRead.readLine();
				System.out.println(s);
				if (s.contains("1"))
				{
					sta = state.LComputer; 
				}
			case LComputer :
				System.out.println("eeee");
				sta = state.Main;
				List<Company> c = a.getAllCompany();
				c.forEach((i)->System.out.println("id : "+i.getId()+"name : " +i.getName()));
				break;
			case Exit:
				break;
			default:
				break;
			}
		}
	}

}
