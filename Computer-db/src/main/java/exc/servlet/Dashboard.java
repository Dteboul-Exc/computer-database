package main.java.exc.servlet;

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

import main.java.exc.model.CompanyDTO;
import main.java.exc.model.ComputerDTO;
import main.java.exc.persistence.OrderByState;
import main.java.exc.service.ServiceComputer;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("search") != null) 
			Search(request,response);
		else	
			Pagination(request,response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 if (request.getParameter("Add") != null) {
			 String name = request.getParameter("name");
			 String introduced = request.getParameter("introduced");
			 String discontinued = request.getParameter("discontinued");
			 CompanyDTO company = CompanyDTO.Builder.newInstance().setId(request.getParameter("company")).build();
			 ComputerDTO newComputer = ComputerDTO.Builder.newInstance().setName(name).setCompany(company).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(company).build();
			 ServiceComputer service = new ServiceComputer();
			 System.out.print("companyID is"  + request.getParameter("company"));
			 try {
				service.addComputer(newComputer);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
		 else if (request.getParameter("Edit") != null) {
			 System.out.print("Adoration");
			 String name = request.getParameter("name");
			 String id = request.getParameter("id");
			 String introduced = request.getParameter("introduced");
			 String discontinued = request.getParameter("discontinued");
			 CompanyDTO company = CompanyDTO.Builder.newInstance().setId(request.getParameter("company")).build();
			 ComputerDTO newComputer = ComputerDTO.Builder.newInstance().setName(name).setCompany(company).setIntroduced(introduced).setDiscontinued(discontinued).build();
			 ServiceComputer service = new ServiceComputer();
			 try {
				service.updateComputer(newComputer);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
			 else if (request.getParameter("selection") != null)
				{
					System.out.println(request.getParameter("selection"));
					String[] List_ID_computer=request.getParameter("selection").split(",");
					ServiceComputer service = new ServiceComputer();
					
					for(String id : List_ID_computer) {
						service.deleteSpecificComputer(Integer.parseInt(id));
					}
				}
	        

		doGet(request, response);
	}
	private void Search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceComputer a = new ServiceComputer();
		List<ComputerDTO> list = new ArrayList<>();
		list = a.Search_Computer(request.getParameter("search"));
		request.setAttribute("currentplace", 1);
		request.setAttribute("recordsPerPage", 200);
		request.setAttribute("max_button", 0);
		request.setAttribute("Computer_list", list);
		request.setAttribute("min_button", 1);
		request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}
	
	
	private void Pagination(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
        int recordsPerPage = 10;
        int currentplace = 1;
        int records = 10;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        if(request.getParameter("recordsPerPage") != null)
        	recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        if(request.getParameter("page") != null)
        	currentplace = Integer.parseInt(request.getParameter("page"));
       
		ServiceComputer a = new ServiceComputer();
		List<ComputerDTO> list = new ArrayList<>();
		if ((request.getParameter("Order") != null)&& ((request.getParameter("Order").equals("computer"))))
			list = a.getAllComputerOrderBy(OrderByState.COMPUTER);
		else if ((request.getParameter("Order") != null)&& ((request.getParameter("Order").equals("company"))))
			list = a.getAllComputerOrderBy(OrderByState.COMPANY);
		else 
			list = a.getAllComputer();
		request.setAttribute("computer", Integer.toString(list.size()));
		long max_button=1;
		if (page != 1)
		{
		if ((page*recordsPerPage  + recordsPerPage) < list.size())
		{
			list = list.subList(page*recordsPerPage, page*recordsPerPage + recordsPerPage);
			page-=1;
			max_button = page +2;
		}
		else
		{
			list = list.subList(page*recordsPerPage, list.size());
			max_button = page;
			page = page-1;	
		}
		}
		else {
			//list = list.subList(page, recordsPerPage );
			max_button = page+1;
		}
		
		request.setAttribute("currentplace", currentplace);
		request.setAttribute("recordsPerPage", recordsPerPage);
		request.setAttribute("max_button", max_button);
		request.setAttribute("Computer_list", list);
		request.setAttribute("min_button", page);
		request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}
	
	
	public void OrderBy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	/*
	 * @Deprecated
	private Map<Integer,Integer> DefinePaginationList(int page,int recordsPerPage,List<ComputerDTO> list )
	{
		Map<Integer,Integer> result = new HashMap<>();
		if ((page*recordsPerPage  + recordsPerPage) < list.size())
		{
			list = list.subList(page*recordsPerPage, page*recordsPerPage + recordsPerPage);
			page-=1;
			max_button = page +2;
			result.put(page-1, page+2);
		}
		else
		{
			list = list.subList(page*recordsPerPage, list.size());
			max_button = page;
			page = page-1;	
		}
		else {
			max_button = page+1;
		}
		return result;
	}
	*/

}
