package com.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
	
	@RequestMapping("/403")
	public ModelAndView Forbidden()
	{
		return new ModelAndView("403");
	}
	@RequestMapping("/error404")
	public ModelAndView notFound()
	{
		return new ModelAndView("404");
	}
	@RequestMapping("/error500")
	public ModelAndView ServerIssue()
	{
		return new ModelAndView("500");
	}

}
