package exc.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exc.model.Computer;

public final class DAOComputer implements DAOComputerInterface {
	
	public List<Computer> getAllComputer() throws SQLException{
		Connection conn = SQLConnect.getConn();
		List<Computer> computer = new ArrayList();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select computer.name,company.name as company ,computer.id,introduced,discontinued,company_id from computer LEFT JOIN company ON computer.company_id = company.id");
        while ( rs.next() ) {
        	Computer tcomputer = new Computer();
            String name = rs.getString("name");
            String company = rs.getString("company");
            int id = rs.getInt("id");
            String introduced = rs.getString("introduced");
            String discontinued = rs.getString("discontinued");
            int company_id = rs.getInt("company_id");
            tcomputer.setId(id);
            tcomputer.setName(name);
            tcomputer.setIntroduced(introduced);
            tcomputer.setDiscontinued(discontinued);
            tcomputer.setCompany_id(company_id);
            tcomputer.setCompany_name(company);
            computer.add(tcomputer);
            
        }
		return computer;
	}
	public Optional<Computer> getSpecificComputer(int id) throws SQLException {
		Connection conn = SQLConnect.getConn();
		Computer computer = new Computer();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from computer where id =" + id);
		if (rs.next())
		{

				String name = rs.getString("name");
				int id1 = rs.getInt("id");
				String introduced = rs.getString("introduced");
				String discontinued = rs.getString("discontinued");
				int company_id = rs.getInt("company_id");
				computer.setId(id);
				computer.setName(name);
				computer.setIntroduced(introduced);
				computer.setDiscontinued(discontinued);
				computer.setCompany_id(company_id);

		}
		else
		{
			computer = null;
		}
		System.out.println("id = " + computer.getId());
		return Optional.ofNullable(computer);
	}
	public int deleteSpecificComputer(int id) throws SQLException {
		Connection conn = SQLConnect.getConn();
		Statement statement = conn.createStatement();
		int rs = statement.executeUpdate("DELETE FROM computer WHERE id =" + id);
		if (rs == 1)
		{

			System.out.println(" Computer successfully deleted");

		}
		else
		{ //TODO Error System
			System.out.println("Error while deleting");
		}
		return rs;
	}
	public int addComputer(String name,String introduced,String discontinued, int company_id) 
	{
		Connection conn = SQLConnect.getConn();
		int computer_id = 2;
		int rs=0;
		String tname = "NULL";
		String tintroduced = "NULL";
		String tdiscontinued = "NULL";

		Statement statement;
		try {
			statement = conn.createStatement();
			
			 //rs = statement.execute("insert into computer (name,introduced, discontinued, company_id) values(" + name +",NULL ,NULL,(select company.id from company where company.id ="+ company_id+"))");
				// rs = statement.executeUpdate("insert into computer (name,introduced, discontinued, company_id) values(" + name +",DATE "+introduced+" ,DATE "+discontinued+",(select company.id from company where company.id ="+ company_id+"))");
			if (name == null)
			{
				tname = "NULL";
			}
			else
			{
				tname = name;
			}
			if (introduced == null)
			{
				tintroduced = "NULL";
			}
			else
			{
				tintroduced = "DATE " + "'"+introduced+"'";
			}
			if (discontinued == null)
			{
				tdiscontinued = "NULL";
			}
			else
			{
				tdiscontinued = "DATE " + "'"+discontinued+"'";;
			}
		    rs = statement.executeUpdate("insert into computer (name,introduced, discontinued, company_id) values('"+tname+"',"+tintroduced+" ,"+tdiscontinued+",(select company.id from company where company.id = "+company_id+"))");
			//rs = statement.execute("insert into computer (name,introduced, discontinued, company_id) values('"+name+"',NULL ,NULL,"+company_id+")");
				System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
		
	}
	public int updateComputer(String name,String introduced,String discontinued, int company_id,int id) 
	{
		Connection conn = SQLConnect.getConn();
		int computer_id = 2;
		int rs=0;
		String tname = "NULL";
		String tintroduced = "NULL";
		String tdiscontinued = "NULL";

		Statement statement;
		try {
			statement = conn.createStatement();
			
			 //rs = statement.execute("insert into computer (name,introduced, discontinued, company_id) values(" + name +",NULL ,NULL,(select company.id from company where company.id ="+ company_id+"))");
				// rs = statement.executeUpdate("insert into computer (name,introduced, discontinued, company_id) values(" + name +",DATE "+introduced+" ,DATE "+discontinued+",(select company.id from company where company.id ="+ company_id+"))");
			if (name == null)
			{
				tname = "NULL";
			}
			else
			{
				tname = name;
			}
			if (introduced == null)
			{
				tintroduced = "NULL";
			}
			else
			{
				tintroduced = "DATE " + "'"+introduced+"'";
			}
			if (discontinued == null)
			{
				tdiscontinued = "NULL";
			}
			else
			{
				tdiscontinued = "DATE " + "'"+discontinued+"'";;
			}
		    rs = statement.executeUpdate("UPDATE computer SET name = '"+tname+"', introduced = "+tintroduced+", discontinued = "+tdiscontinued+", company_id = "+company_id+" WHERE id = " +id);
			//rs = statement.execute("insert into computer (name,introduced, discontinued, company_id) values('"+name+"',NULL ,NULL,"+company_id+")");
				System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
		
	}
}
