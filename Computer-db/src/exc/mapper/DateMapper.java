package exc.mapper;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateMapper {
	public static LocalDateTime StringConverter(String d) throws ParseException
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    LocalDateTime myDateObj = LocalDateTime.parse(d,formatter);
	    System.out.println(myDateObj);
		return myDateObj;
	}
	public static String DateConverter(LocalDateTime myDateObj) throws ParseException
	{
	    System.out.println("Before formatting: " + myDateObj);
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	    String formattedDate = myDateObj.format(myFormatObj);
	    System.out.println("After formatting: " + formattedDate);
		return formattedDate;
	}
}
