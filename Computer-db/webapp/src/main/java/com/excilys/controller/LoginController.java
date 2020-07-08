package com.excilys.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@Controller
public class LoginController {

	@GetMapping(value = { "", "/login" })
	public String Login() {
		return "login";
	}
}
