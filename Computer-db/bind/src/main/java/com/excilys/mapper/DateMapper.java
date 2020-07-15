package com.excilys.mapper;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class DateMapper {
	/**
	 * Mapper that convert the Date value received by the DB into a localDate object
	 *
	 * @param localDate
	 * @return
	 * @throws ParseException
	 */
	public Optional<LocalDate> StringConverter(String localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.print(localDate);
		if (localDate.length() < 1) throw new IllegalArgumentException("param is too short or null");
		if( Integer.parseInt(localDate.substring(0,4)) < 1970 ) throw new IllegalArgumentException("param has not the correct time");
		if ( Integer.parseInt(localDate.substring(0,4)) > 2030 ) throw new IllegalArgumentException("param has not the correct time");
			LocalDateTime date = LocalDateTime.parse(localDate, formatter);
			LocalDate myDateObj = date.toLocalDate();
			Optional<LocalDate> result = Optional.ofNullable(myDateObj);
			return result;


	}

	/**
	 * Mapper used to convert the user input into a localDate Object
	 *
	 * @param localDate
	 * @return
	 * @throws ParseException
	 */
	public Optional<LocalDate> StringConverterInput(String localDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate myDateObj = LocalDate.parse(localDate, formatter);
		Optional<LocalDate> result = Optional.ofNullable(myDateObj);
		return result;
	}

	/**
	 * Mapper used to convert a LocalDate into a format that the Database can
	 * understand the Date Object
	 *
	 * @param myDateObj
	 * @return
	 * @throws ParseException
	 */
	public Optional<String> DateConverter(LocalDate myDateObj) throws ParseException {
		if (myDateObj == null) {
			Optional<String> result = Optional.ofNullable(null);
			return result;
		}

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String formattedDate = myDateObj.format(myFormatObj);
		Optional<String> result = Optional.ofNullable(formattedDate);
		return result;
	}
}
