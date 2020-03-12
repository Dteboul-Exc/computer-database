package exc.mapper;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateMapper {
	/**
	 * Mapper  that convert the Date value received  by the DB into a localDate object
	 * 
	 * @param localDate
	 * @return
	 * @throws ParseException
	 */
	public static LocalDate StringConverter(String localDate) throws ParseException
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    LocalDateTime date = LocalDateTime.parse(localDate,formatter);
	    LocalDate myDateObj = date.toLocalDate();

		return myDateObj;
	}
	
	/**
	 * Mapper used to convert the user input into  a localDate Object
	 * 
	 * @param localDate
	 * @return
	 * @throws ParseException
	 */
	public static LocalDate StringConverterInput(String localDate) throws ParseException
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate myDateObj = LocalDate.parse(localDate,formatter);
		return myDateObj;
	}
	
	
	/**
	 * Mapper used to convert a LocalDate into a format that the Database can understand
	 * 
	 * @param myDateObj
	 * @return
	 * @throws ParseException
	 */
	public static String DateConverter(LocalDate myDateObj) throws ParseException
	{
		if (myDateObj == null) return "NULL";

	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    
	    String formattedDate = myDateObj.format(myFormatObj);

		return formattedDate;
	}
}
