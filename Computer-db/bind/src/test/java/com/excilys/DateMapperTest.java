
package com.excilys;



import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

import com.excilys.configuration.SpringConfiguration;
import com.excilys.mapper.DateMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class DateMapperTest {
	@Autowired
	DateMapper DateMapper;
	
	public DateMapperTest() {
		
	}
	
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
	public void testInvalidStringlInputStringConverterInput() throws ParseException {
		String standard = "TEAFEF";
		assertEquals("2011-02-09",DateMapper.StringConverterInput(standard).get().toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalid1970lInputStringConverterInput() throws ParseException {
		String standard = "1920-02-09";
		DateMapper.StringConverter(standard);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalid2030lInputStringConverterInput() throws ParseException {
		String standard = "2045-02-09";
		DateMapper.StringConverter(standard);
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