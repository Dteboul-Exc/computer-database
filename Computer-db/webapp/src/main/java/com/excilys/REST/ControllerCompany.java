package com.excilys.REST;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.configuration.SpringConfiguration;
import com.excilys.dto.CompanyDTO;
import com.excilys.service.ServiceCompany;

@RestController

public class ControllerCompany {

	@Autowired
	ServiceCompany serviceCompany;


	@GetMapping("/company")
	public CompanyDTO greeting(@RequestParam(value = "id", defaultValue = "0") int id) {
		System.out.print(id);
		return serviceCompany.getSpecificCompany(id).get();
	}

}
