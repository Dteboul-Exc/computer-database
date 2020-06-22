package main.java.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

import main.java.excilys.dto.CompanyDTO;
import main.java.excilys.dto.ComputerDTO;
import main.java.excilys.exception.ServiceComputerException;
import main.java.excilys.persistence.OrderByState;
import main.java.excilys.service.ComputerValidator;
import main.java.excilys.service.ServiceComputer;
import main.java.excilys.spring.SpringConfiguration;

/**
 * Servlet implementation class Starter
 */

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dashboard() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("search") != null)
			Search(request, response);
		else
			Pagination(request, response);

	}

	protected Optional<String> AddValidator(String name, String introduced, String discontinued) {
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

	protected void Add(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		CompanyDTO company = CompanyDTO.Builder.newInstance().setId(request.getParameter("company")).build();
		ComputerDTO newComputer = ComputerDTO.Builder.newInstance().setName(name).setCompany(company)
				.setIntroduced(introduced).setDiscontinued(discontinued).setCompany(company).build();
		ServiceComputer service =  SpringConfiguration.getContext().getBean(ServiceComputer.class);
		System.out.print("companyID is" + request.getParameter("company"));
		try {
			service.addComputer(newComputer);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("errormsg", "all clear");
		if (request.getParameter("Add") != null) {
			String check = AddValidator(request.getParameter("name"), request.getParameter("introduced"),
					request.getParameter("discontinued")).get();
			if (check.equals("clear"))
				Add(request, response);
			else
				request.setAttribute("errormsg", check);

		} else if (request.getParameter("Edit") != null) {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (request.getParameter("selection") != null) {
			System.out.println(request.getParameter("selection"));
			String[] List_ID_computer = request.getParameter("selection").split(",");
			ServiceComputer service =  SpringConfiguration.getContext().getBean(ServiceComputer.class);

			for (String id : List_ID_computer) {
				service.deleteSpecificComputer(Integer.parseInt(id));
			}
		}

		doGet(request, response);
	}


	private void Pagination(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<String, Integer> values = get_Page_Parameters(request);
		long page = values.get("page");
		int recordsPerPage = values.get("recordsPerPage");
		int currentplace = values.get("currentplace");
		String Order = "Computer";
		if (request.getParameter("Order") != null)
			Order = request.getParameter("Order");
		ServiceComputer serviceComputer = SpringConfiguration.getContext().getBean(ServiceComputer.class);
		List<ComputerDTO> list = new ArrayList<>();
		long size = serviceComputer.getCountComputer();
		request.setAttribute("computer", Long.toString(size));
		long max_button = 1;
		if (page != 1) {
			if ((page * recordsPerPage + recordsPerPage) < size) {
				list = SetOrder(request, (currentplace - 1) * recordsPerPage, recordsPerPage);
				page -= 1;
				max_button = page + 2;
			} else {
				list = SetOrder(request, currentplace * recordsPerPage, size - currentplace * recordsPerPage);
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

	public HashMap<String,Integer> get_Page_Parameters(HttpServletRequest request) throws ServletException, IOException {
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

	private void Search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	private List<ComputerDTO> SetOrder(HttpServletRequest request, long start, long end) {
		List<ComputerDTO> list = new ArrayList<>();
		ServiceComputer serviceComputer =  SpringConfiguration.getContext().getBean(ServiceComputer.class);
		if ((request.getParameter("Order") != null) && ((request.getParameter("Order").equals("computer"))))
			list = serviceComputer.getAllComputerOrderBy(OrderByState.COMPUTER, start, end);
		else if ((request.getParameter("Order") != null) && ((request.getParameter("Order").equals("company"))))
			list = serviceComputer.getAllComputerOrderBy(OrderByState.COMPANY, start, end);
		else
			list = serviceComputer.getAllComputer(start, end);
		return list;
	}
}
