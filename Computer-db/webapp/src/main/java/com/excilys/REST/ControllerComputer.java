package com.excilys.REST;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class ControllerComputer {

	@Autowired
	ServiceCompany serviceCompany;
	
	@Autowired
	ServiceComputer serviceComputer;


	@GetMapping("/REST/computer")
	public ResponseEntity<Object> getComputer(@RequestParam(defaultValue = "0") int id) {
		Optional<ComputerDTO> computer = serviceComputer.getSpecificComputer(id);
		if (!computer.isPresent()) return ResponseEntity.status(404).body("computer with id "+ id +"not found");
		return ResponseEntity.ok().body(computer.get());
	}
	
	
	@GetMapping("/REST/computer/search")
	public ResponseEntity<Object> searchComputer(@RequestParam(defaultValue = "0") String name) {
		List<ComputerDTO> computer = serviceComputer.Search_Computer(name);
		return ResponseEntity.ok().body(computer);
	}
	@GetMapping(value = "/REST/computers")
	public ResponseEntity<Object> getComputers(@RequestParam(defaultValue = "10") int offset,
			@RequestParam(defaultValue = "20")int limit ) {
		try
		{
			return ResponseEntity.ok().body(serviceComputer.getAllComputer(offset, limit));
		}
		catch (Exception e)
		{
			return ResponseEntity.status(404).body("Error while using servicecomputer");
		}
		
	}
	
	@GetMapping(value = "/REST/computer/count")
	public ResponseEntity<Object>getCount()
	{
		return ResponseEntity.ok().body(serviceComputer.getCountComputer());
	}
	
	@PostMapping(value = "/REST/POC")
	public ResponseEntity<Object>POCUpdate(@RequestParam(defaultValue = "0") ComputerDTO computer
			)
	{
		int result = serviceComputer.addComputer(computer);
		return ResponseEntity.ok().body(result);
	}
}
