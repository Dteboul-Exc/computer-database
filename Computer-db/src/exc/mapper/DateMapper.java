package exc.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMapper {
	public static Date StringConverter(String d) throws ParseException
	{
		Date date;
		SimpleDateFormat ft = 
		new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        date = ft.parse(d); 
        System.out.println(date);
		return date;
	}
}
