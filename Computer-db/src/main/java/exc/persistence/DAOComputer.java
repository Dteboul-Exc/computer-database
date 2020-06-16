package main.java.exc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.exc.mapper.DateMapper;
import main.java.exc.model.Company;
import main.java.exc.model.Computer;

public class DAOComputer {
	
	private static final String ORDER_BY_COMPANY= "select computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id ORDER BY company.name ";
	private static final String ORDER_BY_COMPUTER= "select computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id ORDER BY computer.name ";
	private static final String SEARCH_COMPUTER="select computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id WHERE LOWER(computer.name) LIKE  ? ;";
	private static final String GET_ALL_COMPUTER = "select computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id";
	private static final String SEARCH_COMPUTER_BY_ID = "SELECT computer.name ,computer.id,introduced,discontinued,company_id ,company.name as company from computer LEFT JOIN company ON computer.company_id = company.id where computer.id = ? ";
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	private static final String ADD_COMPUTER = "INSERT INTO computer (name,introduced, discontinued, company_id) values( ? , ? , ?,(select company.id from company where company.id = ?))";
	
	public Optional<List<Computer>> getAllComputer() throws SQLException, ParseException{
		Connection conn = DataSource.getConn();
		List<Computer> computer = new ArrayList();
		PreparedStatement statement = conn.prepareStatement(GET_ALL_COMPUTER);
		ResultSet resset = statement.executeQuery();
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
        conn.close();

		return result;
	}
	
	
	public List<Computer> getAllComputerOrderBY(OrderByState Order) throws SQLException, ParseException{
		Connection conn = DataSource.getConn();
		List<Computer> computer = new ArrayList();
		Statement stmt = conn.createStatement();
		ResultSet resset;
		switch (Order)
		{
		case COMPANY :
			 resset = stmt.executeQuery(ORDER_BY_COMPANY);
			 System.out.println("company");
			 break;
		case COMPUTER :
			 resset = stmt.executeQuery(ORDER_BY_COMPUTER);
			 System.out.println("computer");
			 break;
		default :
			resset = stmt.executeQuery(ORDER_BY_COMPUTER);
			System.out.println("default");
			break;
		}
		
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
        conn.close();

		return computer;
	}
	public List<Computer> Search_Computer(String name) throws SQLException, ParseException{
		
		Connection conn = DataSource.getConn();
		PreparedStatement statement = conn.prepareStatement(SEARCH_COMPUTER);
		statement.setString(1, name);
		List<Computer> computer = new ArrayList<>();
		ResultSet resset = statement.executeQuery();
		while (resset.next())
		{
			
				Computer tcomputer = Computer.Builder.newInstance().setName(resset.getString("name"))
						.setCompany(Company.Builder.newInstance().setId(resset.getInt("company_id")).setName("company").build())
						.build();
				if(resset.getString("introduced") != null) tcomputer.setIntroduced(DateMapper.StringConverter(resset.getString("introduced") ).get());
				if(resset.getString("discontinued")  != null) tcomputer.setDiscontinued(DateMapper.StringConverter(resset.getString("discontinued") ).get());
				System.out.println(tcomputer.getName());
				computer.add(tcomputer);
		}
        conn.close();

		return computer;
	}
	/**
	 * Search a single computer in the database using its id.
	 * 
	 * @param id the ID of the computer to search in the database
	 * @return the computer if it exist
	 * @throws SQLException
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 */
	public Optional<Computer> get_Computer_By_Id(int id) throws SQLException, ParseException {

		if (id == 0) 
			return Optional.empty();
		Connection conn = DataSource.getConn();
		Computer computer = Computer.Builder.newInstance().build();
		PreparedStatement statement = conn.prepareStatement(SEARCH_COMPUTER_BY_ID);
		statement.setLong(1, id);
		ResultSet resset = statement.executeQuery();
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
        conn.close();

		return Optional.ofNullable(computer);
	}
	
	
	/**
	 * Delete a single computer in the database using its id.
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public int deleteSpecificComputer(int id)  {


		Connection conn;
		try {
			conn = DataSource.getConn();
			Computer computer = Computer.Builder.newInstance().build();
			PreparedStatement statement = conn.prepareStatement(DELETE_COMPUTER);
			statement.setLong(1, id);
			ResultSet resset = statement.executeQuery();

	        conn.close();
	        return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

		
	}
	public static int addComputer(String name,String introduced,String discontinued, long id_company) 
	{
		System.out.print(name);
		Connection conn;
		try {
			conn = DataSource.getConn();
			PreparedStatement statement = conn.prepareStatement(ADD_COMPUTER);
			String tintroduced = "NULL";
			String tdiscontinued = "NULL";
			if (introduced ==null ) statement.setNull(2, java.sql.Types.TIMESTAMP);
			if (discontinued ==null ) statement.setNull(3, java.sql.Types.TIMESTAMP);
			statement.setString(1, name);
			statement.setLong(4, id_company);
			System.out.println(statement);
			int resset = statement.executeUpdate();
	/*			statement = conn.createStatement();
				System.out.println("insert into computer (name,introduced, discontinued, company_id) values('"+tname+"',"+introduced+" ,"+discontinued+",(select company.id from company where company.id = "+id_company+"))");
			    resset = statement.executeUpdate("insert into computer (name,introduced, discontinued, company_id) values('"+tname+"',"+introduced+" ,"+discontinued+",(select company.id from company where company.id = "+id_company+"))");
				System.out.println(resset);
	*/
		        conn.close();
		        return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return 0;
		
	}
	
	
	/**
	 * Method that update a single computer within its Id
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param l
	 * @param id
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int updateComputer(String name,String introduced,String discontinued, long l,int id) throws SQLException 
	{
		Connection conn = DataSource.getConn();
		int resset=0;


		Statement statement;
		
			statement = conn.createStatement();
			System.out.println("UPDATE computer SET name = '"+name+"', introduced = "+introduced+", discontinued = "+discontinued+", company_id = "+l+" WHERE id = " +id);
		    resset = statement.executeUpdate("UPDATE computer SET name = '"+name+"', introduced = "+introduced+", discontinued = "+discontinued+", company_id = "+l+" WHERE id = " +id);
				System.out.println(resset);
				
		        conn.close();
		return 1;
		
	}
}
