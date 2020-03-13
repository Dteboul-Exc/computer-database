package exc.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exc.mapper.DateMapper;
import exc.model.Company;
import exc.model.Computer;

public class DAOComputer {
	
	public static Optional<List<Computer>> getAllComputer() throws SQLException, ParseException{
		Connection conn = SQLConnect.getConn();
		List<Computer> computer = new ArrayList();
		Statement statement = conn.createStatement();
		ResultSet resset = statement.executeQuery("select computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id");
        while ( resset.next() ) {
        	Computer tcomputer = Computer.Builder.newInstance().build();
            String name = resset.getString("name");
            String company = resset.getString("company");
            int id = resset.getInt("id");
            String introduced = resset.getString("introduced");
            String discontinued = resset.getString("discontinued");
            int company_id = resset.getInt("company_id");
            tcomputer.setId(id);
            tcomputer.setName(name);
			if(introduced != null) tcomputer.setIntroduced(DateMapper.StringConverter(introduced).get());
			if(discontinued != null) tcomputer.setDiscontinued(DateMapper.StringConverter(discontinued).get());
			if(company_id!=0)
				{
				
				Company comp = Company.Builder.newInstance().setId(resset.getInt("company_id")).setName(resset.getString("company")).build();
				tcomputer.setCompany(comp);
				}
				else
				{
					Company comp = Company.Builder.newInstance().setId(0).setName("none").build();
					tcomputer.setCompany(comp);
				}
            computer.add(tcomputer);
            
        }
        Optional<List<Computer>> result = Optional.ofNullable(computer);
		return result;
	}
	
	/**
	 * Search a single computer in the database using its id.
	 * 
	 * @param id the ID of the computer to search in the database
	 * @return the computer if it exist
	 * @throws SQLException
	 * @throws ParseException 
	 */
	public static Optional<Computer> getSpecificComputer(int id) throws SQLException, ParseException {
		Connection conn = SQLConnect.getConn();
		Computer computer = Computer.Builder.newInstance().build();
		Statement statement = conn.createStatement();
		ResultSet resset = statement.executeQuery("select computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id where computer.id =" + id);
		if (resset.next())
		{

				String name = resset.getString("name");
				int id1 = resset.getInt("id");
				String introduced = resset.getString("introduced");
				String discontinued = resset.getString("discontinued");
				int company_id = resset.getInt("company_id");
				computer.setId(id);
				computer.setName(name);
				if(introduced != null) computer.setIntroduced(DateMapper.StringConverter(introduced).get());
				if(discontinued != null) computer.setDiscontinued(DateMapper.StringConverter(discontinued).get());
				if(company_id!=0)
				{
					Company comp = Company.Builder.newInstance().setId(resset.getInt("company_id")).setName(resset.getString("company")).build();
					computer.setCompany(comp);
				}
				
		}
		else
		{
			computer = null;
		}
		System.out.println("id = " + computer.getId());
		return Optional.ofNullable(computer);
	}
	
	
	/**
	 * Delete a single computer in the database using its id.
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static int deleteSpecificComputer(int id) throws SQLException {
		Connection conn = SQLConnect.getConn();
		Statement statement = conn.createStatement();
		int resset = statement.executeUpdate("DELETE FROM computer WHERE id =" + id);
		if (resset == 1)
		{

			System.out.println(" Computer successfully deleted");

		}
		else
		{ //TODO Error System
			System.out.println("Error while deleting");
		}
		return resset;
	}
	public static int addComputer(String name,String introduced,String discontinued, int company_id) 
	{
		Connection conn = SQLConnect.getConn();
		int computer_id = 2;
		int resset=0;
		String tname = "NULL";
		String tintroduced = "NULL";
		String tdiscontinued = "NULL";

		Statement statement;
		try {
			statement = conn.createStatement();
			if (name == null)
			{
				tname = "NULL";
			}
			else
			{
				tname = name;
			}
			if (introduced.equals("NULL"))
			{
				tintroduced = "NULL";
			}
			else
			{
				tintroduced = "DATE " + "'"+introduced+"'";
			}
			if (discontinued.equals("NULL"))
			{
				tdiscontinued = "NULL";
			}
			else
			{
				tdiscontinued = "DATE " + "'"+discontinued+"'";;
			}
		    resset = statement.executeUpdate("insert into computer (name,introduced, discontinued, company_id) values('"+tname+"',"+tintroduced+" ,"+tdiscontinued+",(select company.id from company where company.id = "+company_id+"))");
				System.out.println(resset);
		} catch (SQLException exc) {
			exc.printStackTrace();
		}

		return 1;
		
	}
	
	
	/**
	 * Method that update a single computer within its Id
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company_id
	 * @param id
	 * @return
	 */
	public static int updateComputer(String name,String introduced,String discontinued, int company_id,int id) 
	{
		Connection conn = SQLConnect.getConn();
		int computer_id = 2;
		int resset=0;
		String tname = "NULL";
		String tintroduced = "NULL";
		String tdiscontinued = "NULL";

		Statement statement;
		try {
			statement = conn.createStatement();
			if (name == null)
			{
				tname = "NULL";
			}
			else
			{
				tname = name;
			}
			if (introduced.equals("NULL"))
			{
				tintroduced = "NULL";
			}
			else
			{
				tintroduced = "DATE " + "'"+introduced+"'";
			}
			if (discontinued.equals("NULL"))
			{
				tdiscontinued = "NULL";
			}
			else
			{
				tdiscontinued = "DATE " + "'"+discontinued+"'";;
			}
		    resset = statement.executeUpdate("UPDATE computer SET name = '"+tname+"', introduced = "+tintroduced+", discontinued = "+tdiscontinued+", company_id = "+company_id+" WHERE id = " +id);
				System.out.println(resset);
		} catch (SQLException exc) {
			exc.printStackTrace();
		}

		return 1;
		
	}
}
