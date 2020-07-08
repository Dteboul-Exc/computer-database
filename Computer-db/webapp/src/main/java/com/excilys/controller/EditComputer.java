package com.excilys.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;

/**
 * Servlet implementation class EditComputer
 */
@Controller
@RequestMapping(value = "/editComputer")
public class EditComputer {

	@Autowired
	ServiceComputer serviceComputer;

	@Autowired
	ServiceCompany serviceCompany;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView EditC(@RequestParam(required = false, value = "name") String name,
			@RequestParam(required = false, value = "discontinued") String discontinued,
			@RequestParam(required = false, value = "introduced") String introduced,
			@RequestParam(required = false, value = "company") String company,
			@RequestParam(required = false, value = "id") String id) {
		ModelAndView model = new ModelAndView("editComputer");
		try {
			List<CompanyDTO> list_company = serviceCompany.getAllCompany();
			System.out.print(id);
			model.addObject("company", list_company);
			Optional<ComputerDTO> target = serviceComputer.getSpecificComputer(Integer.parseInt(id));
			model.addObject("computerName", target.get().getName());
			model.addObject("introduced", target.get().getIntroduced());
			model.addObject("discontinued", target.get().getDiscontinued());
			model.addObject("id", id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return model;
	}

}
