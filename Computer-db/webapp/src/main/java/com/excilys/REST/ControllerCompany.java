package com.excilys.REST;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.configuration.SpringConfiguration;
import com.excilys.dto.CompanyDTO;
import com.excilys.service.ServiceCompany;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ControllerCompany {

	@Autowired
	ServiceCompany serviceCompany;


	@GetMapping("/REST/company")
	public ResponseEntity<Object> getCompany(@RequestParam(defaultValue = "0") int id) {
		System.out.print(id);
		Optional<CompanyDTO> company = serviceCompany.getSpecificCompany(id);
		if (!company.isPresent()) return ResponseEntity.status(404).body("company with id "+ id +"not found");
		return ResponseEntity.ok().body(company.get());
	}
	
	@GetMapping(value = "/REST/companys")
	public ResponseEntity<Object> getCompanys() {
		return ResponseEntity.ok().body(serviceCompany.getAllCompany());
	}

}
