package exc.mapper;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateMapper {
	public static LocalDate StringConverter(String localDate) throws ParseException
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    LocalDateTime date = LocalDateTime.parse(localDate,formatter);
	    LocalDate myDateObj = date.toLocalDate();
	    System.out.println(myDateObj);
		return myDateObj;
	}
	public static LocalDate StringConverterInput(String localDate) throws ParseException
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate myDateObj = LocalDate.parse(localDate,formatter);
	    System.out.println(myDateObj);
		return myDateObj;
	}
	public static String DateConverter(LocalDate myDateObj) throws ParseException
	{
		if (myDateObj == null) return "NULL";
	    System.out.println("Before formatting: " + myDateObj);
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    
	    String formattedDate = myDateObj.format(myFormatObj);
	    System.out.println("After formatting: " + formattedDate);
		return formattedDate;
	}
}
