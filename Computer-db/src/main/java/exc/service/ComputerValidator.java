package main.java.exc.service;

import main.java.exc.mapper.DateMapper;

public class ComputerValidator {

	public boolean isName(String name)
	{
		if (name.equals("") || (name.equals(null))) return false;
		return true;
	}
	public boolean isDate(String introduced, String discontinued)
	{
		if ((DateMapper.StringConverter(introduced).get().isAfter(DateMapper.StringConverter(discontinued).get()))) {
		return false;
		} else if (((introduced.equals(null) || introduced.equals("") && (!discontinued.equals(null) && (!discontinued.equals(null))))))
		{
			return false;	
		}
		return true;
		
	}
}
