package com.excilys.configuration;

import javax.validation.Payload;

public @interface AddComputerConstraint {
	String message() default "Invalid phone number";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
