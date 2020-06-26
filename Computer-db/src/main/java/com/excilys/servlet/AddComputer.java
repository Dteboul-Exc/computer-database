package com.excilys.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;
import com.excilys.spring.SpringConfiguration;

/**
 * Servlet implementation class AddComputer
 */

@Controller
@RequestMapping(value = "/addComputer")
public class AddComputer{
       
	@Autowired
	ServiceComputer serviceComputer;
	
	@Autowired
	ServiceCompany serviceCompany;



	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView AddC()
	{
		Optional<List<CompanyDTO>> list_company = serviceCompany.getAllCompany();
		ModelAndView model = new ModelAndView("addComputer");
		model.addObject("company", list_company.get());
		return model;
	}

}
