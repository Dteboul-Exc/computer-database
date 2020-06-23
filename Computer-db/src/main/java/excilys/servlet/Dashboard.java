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
			Dashboardmethods.Search(request, response);
		else
			Dashboardmethods.Pagination(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("errormsg", "all clear");
		if (request.getParameter("Add") != null) {
			String check = Dashboardmethods.AddValidator(request.getParameter("name"), request.getParameter("introduced"),
					request.getParameter("discontinued")).get();
			if (check.equals("clear"))
				Dashboardmethods.Add(request, response);
			else
				request.setAttribute("errormsg", check);

		} else if (request.getParameter("Edit") != null) {
			Dashboardmethods.Edit(request);
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





}
