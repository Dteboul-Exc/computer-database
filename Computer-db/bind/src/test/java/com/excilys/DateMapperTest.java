
package com.excilys;



import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertEquals;
import com.excilys.mapper.DateMapper;

public class DateMapperTest {
	
	public DateMapperTest() {
		
	}
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testNormalInputStringConverter() {
		String standard = "2011-02-09 00:00:00";
		assertEquals("2011-02-09",DateMapper.StringConverter(standard).get().toString());
	}
	
	
	@Test
	public void testNormalInputStringConverterInput() throws ParseException {
		String standard = "2011-02-09";
		assertEquals("2011-02-09",DateMapper.StringConverterInput(standard).get().toString());
	}
	
	@Test(expected = DateTimeParseException.class)
	public void testInvalidlInputStringConverterInput() throws ParseException {
		String standard = "TEAFEF";
		assertEquals("2011-02-09",DateMapper.StringConverterInput(standard).get().toString());
	}
	
	@Test
	public void testNormalDateConverter() throws ParseException
	{
		LocalDate date =  LocalDate.parse("1990-12-12");
		assertEquals("1990-12-12",DateMapper.DateConverter(date).get());
	}
	
	@Test
	public void testnullDateConverter() throws ParseException
	{
		assertEquals(Optional.empty(),DateMapper.DateConverter(null));
	}
	

	

}