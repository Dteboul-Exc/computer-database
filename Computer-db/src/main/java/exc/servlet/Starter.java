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

import main.java.exc.model.CompanyDTO;
import main.java.exc.model.ComputerDTO;
import main.java.exc.service.ServiceComputer;

/**
 * Servlet implementation class Starter
 */
@WebServlet("/dashboard")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 1;
        int recordsPerPage = 10;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        if(request.getParameter("recordsPerPage") != null)
        	recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
		ServiceComputer a = new ServiceComputer();
		List<ComputerDTO> list = new ArrayList<>();
		list = a.getAllComputer().get();
		List<ComputerDTO> content = new ArrayList<>();
		//content.subList(1, 10);
		for(int i=0;i< content.size();i++)
		{
			System.out.print(i);
		}
		request.setAttribute("Computer_list", content);
		request.setAttribute("computer", Integer.toString(content.size()));
		request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("selection") != null)
		{
			System.out.println(request.getParameter("selection"));
			String[] List_ID_computer=request.getParameter("selection").split(",");
			ServiceComputer service = new ServiceComputer();
			
			for(String id : List_ID_computer) {
				service.deleteSpecificComputer(Integer.parseInt(id));
			}
		}
		if (request.getParameter("AddComputer") != null) {
			Add_Computer(request,response);
		} else if (request.getParameter("EditComputer") != null) {
			Edit_Computer(request,response);
		}
		else if (request.getParameter("selection") != null)
		{
			System.out.println(request.getParameter("selection"));
			String[] List_ID_computer=request.getParameter("selection").split(",");
			ServiceComputer service = new ServiceComputer();
			
			for(String id : List_ID_computer) {
				service.deleteSpecificComputer(Integer.parseInt(id));
			}
		}
		else if (request.getParameter("order-by") !=null)
		{
			
		}

		doGet(request, response);
	}
	
	
	public void Add_Computer(HttpServletRequest request, HttpServletResponse response)
	{
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		CompanyDTO company = CompanyDTO.Builder.newInstance().setId(request.getParameter("company")).build();
		ComputerDTO newComputer = ComputerDTO.Builder.newInstance().setName(name).setCompany(company)
				.setIntroduced(introduced).setDiscontinued(discontinued).setCompany(company).build();
		ServiceComputer service = new ServiceComputer();
		try {
			service.addComputer(newComputer);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void Edit_Computer(HttpServletRequest request, HttpServletResponse response)
	{
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		System.out.print(id);
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		CompanyDTO company = CompanyDTO.Builder.newInstance().setId(request.getParameter("company")).build();
		ComputerDTO newComputer = ComputerDTO.Builder.newInstance().setName(name).setCompany(company)
				.setIntroduced(introduced).setDiscontinued(discontinued).setId(id).build();
		ServiceComputer service = new ServiceComputer();
		try {
			service.updateComputer(newComputer);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
