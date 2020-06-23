package main.java.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.excilys.model.Company;

public class ResultSetCompanyMapper {
	private Company toCompany(ResultSet rs) throws SQLException
	{
		Company tCompany = Company.Builder.newInstance().setId(rs.getInt("id")).setName(rs.getString("name"))
				.build();
		return tCompany;
	}
}
