package com.excilys.servlet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.dto.ComputerDTO;
import com.excilys.persistence.OrderByState;
import com.excilys.service.ServiceComputer;

@Component
public class ControllerMethods {
	
	@Autowired
	ServiceComputer serviceComputer;
	protected int GetNumberOFComputer( ) { 
		
		return serviceComputer.getCountComputer();
	}
	protected List<ComputerDTO> SetOrder( int start, int end,String Order) {
		List<ComputerDTO> list = new ArrayList<>();

		if ((Order != null) && ((Order.equals("computer"))))
			list = serviceComputer.getAllComputerOrderBy("computer.name", start, end);
		else if ((Order != null) && ((Order.equals("company"))))
			list = serviceComputer.getAllComputerOrderBy("company.name", start, end);
		else
			list = serviceComputer.getAllComputer(Math.abs(start), Math.abs(end));
		return list;
	}
}
