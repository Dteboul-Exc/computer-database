package com.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ServiceComputerException;
import com.excilys.persistence.OrderByState;
import com.excilys.service.ComputerValidator;
import com.excilys.service.ServiceComputer;
import com.excilys.spring.SpringConfiguration;

/**
 * Methods used exclusively by Dashboard, allowing validation of inputs ,creation, edition and suppression of computer
 * @author dteboul
 *
 */
public class Dashboardmethods {
	/**
	 * Invoke many validators to check if the input of add computer is valid 
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @return
	 */
	protected static Optional<String> AddValidator(String name, String introduced, String discontinued) {
		try {
			ComputerValidator.isName(name);
			ComputerValidator.isDate(introduced, discontinued);
			ComputerValidator.isDateValid(introduced, discontinued);
		} catch (ServiceComputerException e) {
			e.printStackTrace();
			return Optional.of(e.toString());
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			return Optional.of(e.toString());
		}
		return Optional.of("clear");
	}

	/**
	 * Methods invoked when receiving an Add request.
	 * @param request
	 * @param response
	 */
	protected static  void Add(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		CompanyDTO company = CompanyDTO.Builder.newInstance().setId(request.getParameter("company")).build();
		ComputerDTO newComputer = ComputerDTO.Builder.newInstance().setName(name).setCompany(company)
				.setIntroduced(introduced).setDiscontinued(discontinued).setCompany(company).build();
		ServiceComputer service =  SpringConfiguration.getContext().getBean(ServiceComputer.class);
		try {
			service.addComputer(newComputer);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	/**
	 * return the page paramters that were previously set on dashboard (default value in case they were not or invalid )
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private static HashMap<String,Integer> get_Page_Parameters(HttpServletRequest request) throws ServletException, IOException {
		int page = 1;
		int recordsPerPage = 10;//
		int currentplace = 1;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		if (request.getParameter("recordsPerPage") != null)
			recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
		if (request.getParameter("page") != null)
			currentplace = Integer.parseInt(request.getParameter("page"));
		 HashMap<String, Integer> values = new HashMap<String, Integer>();
		 values.put("page", page);
		 values.put("recordsPerPage", recordsPerPage);
		 values.put("currentplace", currentplace);
		 return values;
	}

	/**
	 * Show to the user a list representing possible matches
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected static void Search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceComputer a =  SpringConfiguration.getContext().getBean(ServiceComputer.class);
		List<ComputerDTO> list = new ArrayList<>();
		list = a.Search_Computer(request.getParameter("search"));
		request.setAttribute("currentplace", 1);
		request.setAttribute("recordsPerPage", 200);
		request.setAttribute("max_button", 0);
		request.setAttribute("Computer_list", list);
		request.setAttribute("min_button", 1);
		request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * Used in case the end user want to sort the list of computers
	 * @param request
	 * @param start
	 * @param end
	 * @return
	 */
	protected static List<ComputerDTO> SetOrder(HttpServletRequest request, int start, int end) {
		List<ComputerDTO> list = new ArrayList<>();
		ServiceComputer serviceComputer =  SpringConfiguration.getContext().getBean(ServiceComputer.class);
		if ((request.getParameter("Order") != null) && ((request.getParameter("Order").equals("computer"))))
			list = serviceComputer.getAllComputerOrderBy(OrderByState.COMPUTER, start, end);
		else if ((request.getParameter("Order") != null) && ((request.getParameter("Order").equals("company"))))
			list = serviceComputer.getAllComputerOrderBy(OrderByState.COMPANY, start, end);
		else
			list = serviceComputer.getAllComputer(Math.abs(start), Math.abs(end));
		return list;
	}
	
	
	/**
	 * 
	 * Handle pagination logic and next and previous button apparences 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected static void Pagination(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<String, Integer> values = get_Page_Parameters(request);
		int page = values.get("page");
		int recordsPerPage = values.get("recordsPerPage");
		int currentplace = values.get("currentplace");
		String Order = "Computer";
		if (request.getParameter("Order") != null)
			Order = request.getParameter("Order");
		ServiceComputer serviceComputer = SpringConfiguration.getContext().getBean(ServiceComputer.class);
		List<ComputerDTO> list = new ArrayList<>();
		long size = serviceComputer.getCountComputer();
		request.setAttribute("computer", Long.toString(size));
		int max_button = 1;
		if (page != 1) {
			if ((page * recordsPerPage + recordsPerPage) < size) {
				list = SetOrder(request, (currentplace - 1) * recordsPerPage, recordsPerPage);
				page -= 1;
				max_button = page + 2;
			} else {
				list = SetOrder(request, currentplace * recordsPerPage, (int) (size - currentplace * recordsPerPage));
				max_button = page;
				page = page - 1;
			}
		} else {
			list = SetOrder(request, 0, recordsPerPage);
			max_button = page + 1;
		}
		request.setAttribute("Order", Order);
		request.setAttribute("currentplace", currentplace);
		request.setAttribute("recordsPerPage", recordsPerPage);
		request.setAttribute("max_button", max_button);
		request.setAttribute("Computer_list", list);
		request.setAttribute("min_button", page);
		request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}
	
	
	/**
	 * Used when receiving an edit request
	 * @param request
	 */
	protected  static void Edit(HttpServletRequest request)
	{
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		CompanyDTO company = CompanyDTO.Builder.newInstance().setId(request.getParameter("company")).build();
		ComputerDTO newComputer = ComputerDTO.Builder.newInstance().setName(name).setCompany(company)
				.setIntroduced(introduced).setDiscontinued(discontinued).setId(id).build();
		ServiceComputer service =  SpringConfiguration.getContext().getBean(ServiceComputer.class);
		try {
			service.updateComputer(newComputer);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
