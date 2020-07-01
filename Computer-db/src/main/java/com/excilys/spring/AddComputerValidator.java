package com.excilys.spring;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddComputerValidator implements ConstraintValidator<AddComputerConstraint,String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initialize(AddComputerConstraint constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

}
