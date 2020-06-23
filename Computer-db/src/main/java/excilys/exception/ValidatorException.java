package main.java.excilys.exception;

import java.time.LocalDate;

import org.springframework.stereotype.Component;



/**
 * @author dteboul
 * Class used by the validator in case of an excpetion
 *
 */
public class ValidatorException extends Exception {
	ValidatorException(){super();}
	ValidatorException(String e){super(e);}
	ValidatorException(LocalDate introduced,LocalDate dicontinued) {};

}
