package main.java.exc.mapper;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.stereotype.Component;



public class DateMapper {
	/**
	 * Mapper  that convert the Date value received  by the DB into a localDate object
	 * 
	 * @param localDate
	 * @return
	 * @throws ParseException
	 */
	public static Optional<LocalDate> StringConverter(String localDate) 
	{
		System.out.println("argument : " + localDate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
	    LocalDateTime date = LocalDateTime.parse(localDate,formatter);
	    LocalDate myDateObj = date.toLocalDate();
	    Optional<LocalDate> result = Optional.ofNullable(myDateObj);
		return result;
		}
		catch (DateTimeParseException e) {
			    LocalDateTime date = LocalDateTime.parse("1910-01-01 00:00:00",formatter);
			    LocalDate myDateObj = date.toLocalDate();
			    Optional<LocalDate> result = Optional.ofNullable(myDateObj);
				return result;
		}
		catch (NullPointerException e) {
		    Optional<LocalDate> result = Optional.ofNullable(null);
			return result;
	}
		
	}
	
	/**
	 * Mapper used to convert the user input into  a localDate Object
	 * 
	 * @param localDate
	 * @return
	 * @throws ParseException
	 */
	public static Optional<LocalDate> StringConverterInput(String localDate) 
	{
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate myDateObj = LocalDate.parse(localDate,formatter);
	    Optional<LocalDate> result = Optional.ofNullable(myDateObj);
		return result;
	}
	
	
	/**
	 * Mapper used to convert a LocalDate into a format that the Database can understand the Date Object
	 * 
	 * @param myDateObj
	 * @return
	 * @throws ParseException
	 */
	public static Optional<String> DateConverter(LocalDate myDateObj) throws ParseException
	{
		if (myDateObj == null) 
			{
		    Optional<String> result = Optional.ofNullable("NULL");
			return result;
			}

	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    
	    String formattedDate = myDateObj.format(myFormatObj);
	    Optional<String> result = Optional.ofNullable(formattedDate);
		return result;
	}
}
