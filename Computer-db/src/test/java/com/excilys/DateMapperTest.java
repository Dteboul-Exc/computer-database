package com.excilys;



import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.excilys.mapper.DateMapper;


class DateMapperTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	void testNormalInputStringConverter() {
		String standard = "2011-02-09 00:00:00";
		assertEquals("2011-02-09",DateMapper.StringConverter(standard).get().toString());
	}
	
	@Test
	void testInvalidInputStringConverter() {
		thrown.expect(DateTimeParseException.class);
		String standard = "ZAEAZEAZEAZE";
		assertEquals("1910-01-01",DateMapper.StringConverter(standard).get().toString());
	}
	
	@Test
	void testNormalInputStringConverterInput() throws ParseException {
		String standard = "2011-02-09";
		assertEquals("2011-02-09",DateMapper.StringConverterInput(standard).get().toString());
	}
	

	

}
