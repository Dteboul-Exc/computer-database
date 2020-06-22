package main.java.excilys.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import main.java.excilys.dto.CompanyDTO;
import main.java.excilys.dto.ComputerDTO;
import main.java.excilys.service.ServiceCompany;
import main.java.excilys.service.ServiceComputer;
import main.java.excilys.spring.SpringConfiguration;

/**
 * Servlet implementation class EditComputer
 */

@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("id") != null) {
			ServiceComputer comp =  SpringConfiguration.getContext().getBean(ServiceComputer.class);
			ServiceCompany c = SpringConfiguration.getContext().getBean(ServiceCompany.class);
			System.out.print("everybo");
			try {
				Optional<List<CompanyDTO>> list_company = c.getAllCompany();
				request.setAttribute("company", list_company.get());
				Optional<ComputerDTO> target = comp.getSpecificComputer(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("computerName", target.get().getName());
				request.setAttribute("introduced", target.get().getIntroduced());
				request.setAttribute("discontinued", target.get().getDiscontinued());
				request.setAttribute("id", request.getParameter("id"));

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		request.getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}
