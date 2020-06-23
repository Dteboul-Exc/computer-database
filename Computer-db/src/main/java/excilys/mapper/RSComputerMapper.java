package main.java.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import main.java.excilys.model.Company;
import main.java.excilys.model.Computer;
@Service
public class RSComputerMapper  implements RowMapper<Computer>{
	private Optional<Computer> toComputer(ResultSet rs) throws SQLException
	{
		Computer tcomputer = Computer.Builder.newInstance().setId(rs.getInt("id")).setName(rs.getString("name")).build();
		if (rs.getString("introduced") != null)
			tcomputer.setIntroduced(DateMapper.StringConverter(rs.getString("introduced")).get());
		if (rs.getString("discontinued") != null)
			tcomputer.setDiscontinued(DateMapper.StringConverter(rs.getString("discontinued")).get());
		if (rs.getInt("company_id") != 0) {
			Company comp = Company.Builder.newInstance().setId(rs.getInt("company_id"))
					.setName(rs.getString("company")).build();
			tcomputer.setCompany(comp);
		}
		
		return Optional.ofNullable(tcomputer);
	}

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return toComputer(rs).get();
	}
}
