package com.excilys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.dto.CompanyDTO;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;

/**
 * Servlet implementation class AddComputer
 */

@Controller
@RequestMapping(value = "/addComputer")
public class AddComputer {

	@Autowired
	ServiceComputer serviceComputer;

	@Autowired
	ServiceCompany serviceCompany;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView AddC() {
		List<CompanyDTO> list_company = serviceCompany.getAllCompany();
		ModelAndView model = new ModelAndView("addComputer");
		model.addObject("company", list_company);
		return model;
	}

}
