package main.java.exc.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.exc.model.CompanyDTO;
import main.java.exc.model.ComputerDTO;
import main.java.exc.service.ServiceCompany;
import main.java.exc.service.ServiceComputer;

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
			ServiceComputer comp = new ServiceComputer();
			ServiceCompany c = new ServiceCompany();
			System.out.print("everybo");
			try {
				Optional<List<CompanyDTO>> list_company = c.getAllCompany();
				request.setAttribute("company", list_company.get());
				Optional<ComputerDTO> target = comp.getSpecificComputer(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("computerName", target.get().getName());
				request.setAttribute("introduced", target.get().getIntroduced());
				request.setAttribute("discontinued", target.get().getDiscontinued());
				request.setAttribute("id", request.getParameter("id"));

			} catch (NumberFormatException | ClassNotFoundException e) {
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
