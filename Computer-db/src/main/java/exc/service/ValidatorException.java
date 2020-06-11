package main.java.exc.service;

import java.time.LocalDate;

public class ValidatorException extends Exception {
	ValidatorException() {
		super();
	}

	ValidatorException(String e) {
		super(e);
	}

	ValidatorException(LocalDate introduced, LocalDate dicontinued) {
	};

}
