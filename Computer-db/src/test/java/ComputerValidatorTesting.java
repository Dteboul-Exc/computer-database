package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import main.java.exc.service.ComputerValidator;
import main.java.exc.service.ServiceComputer;
import main.java.exc.servlet.AddComputer;

class ComputerValidatorTesting {
	
	ComputerValidator validator = mock(ComputerValidator.class);
	
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 
    
	@Test
	void testSuccessfulComputer() {
		ServiceComputer t  = new ServiceComputer(validator); 
		when(validator.isName("test"))
	}

}
