package com.excilys.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ServiceComputerException;
import com.excilys.persistence.OrderByState;
import com.excilys.service.ComputerValidator;
import com.excilys.service.ServiceComputer;
import com.excilys.spring.SpringConfiguration;


@Controller
@RequestMapping(value = "/dashboard")
public class BaseController {
	@Autowired
	ServiceComputer serviceComputer;
		@Autowired 
		ControllerMethods method;
	   @RequestMapping(method = RequestMethod.GET)
		public ModelAndView dashboard(	@RequestParam(required = false, value = "page") Integer page, @RequestParam(required = false, value = "recordsPerPage") Integer recordsPerPage,
				@RequestParam(required = false, value = "currentplace") Integer currentplace, @RequestParam(required = false, value = "Order") String Order,
				@RequestParam(required = false, value = "search") String search) {
		   		if (page ==null){ page = 1; currentplace = 1;}
		   		if (currentplace == null) currentplace = 1;
		   		if (recordsPerPage ==null) recordsPerPage = 10;
		   		if (Order == null ) Order = "Computer";
		   		
				List<ComputerDTO> list = new ArrayList<>();
				int size = method.GetNumberOFComputer();
				ModelAndView model = new ModelAndView("dashboard");
				int max_button = 1;
				System.out.println(" page is :" + page);
				if (page.intValue() > 1) {
					if ((page * recordsPerPage + recordsPerPage) < size) {
						list = SetOrder((currentplace - 1) * recordsPerPage, recordsPerPage, Order);
						page -= 1;
						max_button = page + 2;
					} else {
						list =SetOrder(currentplace * recordsPerPage, size - currentplace * recordsPerPage, Order);
						max_button = page;
						page = page - 1;
					}
				} else {
					System.out.println(" Entered set order");
					list = SetOrder( 0, recordsPerPage,Order);
					max_button = page + 1;
				}
				System.out.println(Order + " : " +recordsPerPage+ " : " +max_button+ " : " +page+ " : " + list+ " : " +currentplace+ " : " + model);
				setParameters(Order,recordsPerPage,max_button,page, list, currentplace, size,model);
			return model;
		}
	   
	   
	   
	   
	   private void setParameters(String Order,int recordsPerPage,int max_button,int min_button,List<ComputerDTO> list,int currentplace,int computer,ModelAndView model)
	   {
		   model.addObject("Order",Order).
		   addObject("recordsPerPage",recordsPerPage).
		   addObject("max_button",max_button).
		   addObject("min_button", min_button).addObject("Computer_list",list).addObject("currentplace",currentplace).addObject("computer",Integer.toString(computer));
	   }
	   
		
		protected int GetNumberOFComputer( ) { 
			
			return serviceComputer.getCountComputer();
		}
		protected List<ComputerDTO> SetOrder( int start, int  end,String Order) {
			List<ComputerDTO> list = new ArrayList<>();

			if ((Order != null) && ((Order.equals("computer"))))
				list = serviceComputer.getAllComputerOrderBy(OrderByState.COMPUTER, start, end);
			else if ((Order != null) && ((Order.equals("company"))))
				list = serviceComputer.getAllComputerOrderBy(OrderByState.COMPANY, start, end);
			else
				list = serviceComputer.getAllComputer(Math.abs(start), Math.abs(end));
			return list;
		}
		protected  void Edit(String name,String id,String introduced, String discontinued,String c)
		{

			CompanyDTO company = CompanyDTO.Builder.newInstance().setId(c).build();
			ComputerDTO newComputer = ComputerDTO.Builder.newInstance().setName(name).setCompany(company)
					.setIntroduced(introduced).setDiscontinued(discontinued).setId(id).build();
			ServiceComputer service =  SpringConfiguration.getContext().getBean(ServiceComputer.class);
			try {
				service.updateComputer(newComputer);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		protected  List<ComputerDTO> SetOrder(HttpServletRequest request, int start, int end) {
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

}
