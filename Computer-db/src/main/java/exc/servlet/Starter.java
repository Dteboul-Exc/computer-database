package main.java.exc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.exc.model.ComputerDTO;
import main.java.exc.service.ServiceComputer;

/**
 * Servlet implementation class Starter
 */
@WebServlet("/Starter")
public class Starter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Starter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ServiceComputer a = new ServiceComputer();
		Optional<List<ComputerDTO>> list;
		try {
			list = a.getAllComputer();
			request.setAttribute("Computer_list", list.get());
			request.setAttribute("computer", Integer.toString(list.get().size()));
			request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 if (request.getParameter("Add") != null) {
			 System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			 String name = request.getParameter("name");
			 String introduced = request.getParameter("introduced");
			 String discontinued = request.getParameter("discontinued");
			 String company = request.getParameter("company");
			 ComputerDTO newComputer = ComputerDTO.Builder.newInstance().setName(name).setCompany(company).setIntroduced(introduced).setDiscontinued(discontinued).build();
			 ServiceComputer service = new ServiceComputer();
			 try {
				service.addComputer(name, introduced, discontinued, Integer.parseInt(company));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
		doGet(request, response);
	}

}
