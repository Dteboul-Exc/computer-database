package main.java.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import main.java.excilys.model.Company;


@Service
public class RSCompanyMapper implements RowMapper<Company> {
	private Optional<Company> toCompany(ResultSet rs) throws SQLException
	{
		Company tCompany = Company.Builder.newInstance().setId(rs.getInt("id")).setName(rs.getString("name"))
				.build();
		return Optional.ofNullable(tCompany);
	}

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		return toCompany(rs).get();
	}
}
