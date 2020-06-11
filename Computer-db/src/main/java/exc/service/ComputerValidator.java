package main.java.exc.service;

import main.java.exc.mapper.DateMapper;
import main.java.exc.model.Company;

public class ComputerValidator {

	public boolean isName(String name) throws ComputerException {
		if (name.equals("") || (name.equals(null)))
			throw new ComputerException("Name isn't set");
		return true;
	}

	public boolean isDate(String introduced, String discontinued) throws ComputerException {
		if ((DateMapper.StringConverter(introduced).get().isAfter(DateMapper.StringConverter(discontinued).get()))) {
			throw new ComputerException("Introduced must be before discontinued");
		} else if (((introduced.equals(null)
				|| introduced.equals("") && (!discontinued.equals(null) && (!discontinued.equals(null)))))) {
			throw new ComputerException("Discontinued was set up while introduced was not initialized");
		}
		return true;

	}

	public boolean isCompany(Company company) throws ComputerException {
		if (company.equals(null)) {
			throw new ComputerException("Company isn't set");
		}
		return true;

	}
}
