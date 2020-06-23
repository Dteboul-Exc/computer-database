package main.java.excilys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import main.java.excilys.exception.ServiceComputerException;
import main.java.excilys.mapper.DateMapper;
import main.java.excilys.model.Company;


public class ComputerValidator  {
	private static final Logger lOG =
            LoggerFactory.getLogger(ComputerValidator.class);

	public static boolean isComputerId(int id) throws ServiceComputerException
	{
		if (id <= 0) throw new ServiceComputerException("Id from computer isn't set correctly");
		return true;
	}
	
	public static boolean isCompanyId(int id) throws ServiceComputerException
	{
		if (id < 0) throw new ServiceComputerException("Id from company isn't set correctly");
		return true;
	}
	public static boolean isName(String name) throws ServiceComputerException
	{
		if (name.equals("") || (name.equals(null))) throw new ServiceComputerException("Name isn't set");
		return true;
	}
	public static boolean isDate(String introduced, String discontinued) throws ServiceComputerException
	{
		if ((DateMapper.StringConverter(introduced).get().isAfter(DateMapper.StringConverter(discontinued).get()))) {
			throw new ServiceComputerException("Introduced must be before discontinued");	
		} else if (((introduced.equals(null) || introduced.equals("") && (!discontinued.equals(null) && (!discontinued.equals(""))))))
		{
			throw new ServiceComputerException("Discontinued was set up while introduced was not initialized");	
		}
		return true;
		
	}
	public static boolean isCompany(Company company) throws ServiceComputerException
	{
		if (company.equals(null)) {
			throw new ServiceComputerException("Company isn't set properly");
		}
		return true;
		
	}
	public static boolean isDateValid(String introduced, String discontinued) throws ServiceComputerException
	{
		lOG.debug("introduced and discontinued size : "+introduced.length() + " : " + discontinued.length());
		if((introduced.equals(null) ||introduced.equals("")) && (discontinued.equals(null) ||discontinued.equals(""))) return true;
		if ((introduced.isEmpty() ) && (discontinued.isEmpty() ))return true;
		if (((introduced.length() != 10) && (introduced.length() != 0) )) throw new ServiceComputerException("invalid Date , size  of introduced  is not valid");			
		if (introduced.contains("/")) throw new ServiceComputerException("invalid introduced Date format, must not use / in the input string");
		if ((Integer.parseInt(introduced.substring(5,7))> 12) && (introduced.length() != 0)) throw new ServiceComputerException("Date Mismatch. Month is placed in place of the day");
		if (Integer.parseInt(introduced.substring(8))> 31) throw new ServiceComputerException("Date Mismatch. day is over 31");
		try
		{		
			if (introduced.length() != 0)DateMapper.StringConverterInput(introduced);
			
			if (discontinued.length() != 0)
				{
				if (discontinued.contains("/")) throw new ServiceComputerException("invalid discontinued Date format, must not use / in the input string");
				if(discontinued.length() != 10) throw new ServiceComputerException("invalid Date , size of discontinued  is not valid");	
				if (Integer.parseInt(discontinued.substring(5,7))> 12) throw new ServiceComputerException("Date Mismatch. Month is placed in place of the day");
				if (Integer.parseInt(discontinued.substring(8))> 31) throw new ServiceComputerException("Date Mismatch. day is over 31");
				DateMapper.StringConverterInput(discontinued);
				}
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
}
