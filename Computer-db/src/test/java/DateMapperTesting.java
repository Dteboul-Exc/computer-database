package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import main.java.exc.mapper.DateMapper;

class DateMapperTesting {

	@Test
	void testNormalInputStringConverter() {
		String standard = "2011-02-09 00:00:00";
		try {
			
			Optional<LocalDate> test =  DateMapper.StringConverter(standard);
			assertEquals("2011-02-09",test);
			
		} catch (ParseException e) {
			fail("Parse exception error " + e);
		}
		
		
	}
	
	@Test
	void testInvalidInputStringConverter() {
		fail("Not yet implemented");
	}
	
	@Test
	void testInputbefore1970StringConverter() {
		fail("Not yet implemented");
	}

}
