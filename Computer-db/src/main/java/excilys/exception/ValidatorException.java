package main.java.excilys.exception;

import java.time.LocalDate;

import org.springframework.stereotype.Component;



public class ValidatorException extends Exception {
	ValidatorException(){super();}
	ValidatorException(String e){super(e);}
	ValidatorException(LocalDate introduced,LocalDate dicontinued) {};

}
