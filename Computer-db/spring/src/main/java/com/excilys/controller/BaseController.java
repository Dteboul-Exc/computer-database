package com.excilys.controller;

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

import com.excilys.configuration.SpringConfiguration;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ServiceComputerException;
import com.excilys.service.ComputerValidator;
import com.excilys.service.ServiceComputer;



@Controller
@RequestMapping(value = "/dashboard")
public class BaseController {
	@Autowired
	ServiceComputer serviceComputer;
		@Autowired 
		ControllerMethods method;
		
		
		
		   @RequestMapping(method = RequestMethod.POST)
		   public String postHandlerAdd(@RequestParam(required = false, value = "Add")String Add,@RequestParam(required = false, value = "selection")String selection,
				   @RequestParam(required = false, value = "name") String name,@RequestParam(required = false, value = "Edit")String edit,
				   @RequestParam(required = false, value = "discontinued") String discontinued,@RequestParam(required = false, value = "introduced") String introduced,
				   @RequestParam(required = false, value = "company") String company,@RequestParam(required = false, value = "id") String id)
		    {
			   ModelAndView model = new ModelAndView("dashboard");
			   model.addObject("errormsg","all clear");
				if (Add != null) {
					String check = Dashboardmethods.AddValidator(name,introduced,
							discontinued).get();
					if (check.equals("clear"))
					{
						CompanyDTO c = CompanyDTO.Builder.newInstance().setId(company).build();
						ComputerDTO newComputer = ComputerDTO.Builder.newInstance().setName(name).setCompany(c)
								.setIntroduced(introduced).setDiscontinued(discontinued).setCompany(c).build();
						try {
							serviceComputer.addComputer(newComputer);
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
					else
						model.addObject("errormsg",check);
				}
				else if (selection != null) {
					String[] List_ID_computer = selection.split(",");
					ServiceComputer service =  SpringConfiguration.getContext().getBean(ServiceComputer.class);
					for (String l : List_ID_computer) {
						service.deleteSpecificComputer(Integer.parseInt(l));
					}
				}
				else if (edit != null) {
					   Edit(name,id,introduced, discontinued,company);
				   }
				return "redirect:/dashboard";
		   }
		   /*
		   @RequestMapping(method = RequestMethod.POST)
		   public String PostHandlerSearch(@RequestParam(required = false, value = "selection")String selection)
		   {
			if (selection != null) {
				String[] List_ID_computer = selection.split(",");
				ServiceComputer service =  SpringConfiguration.getContext().getBean(ServiceComputer.class);
				for (String id : List_ID_computer) {
					service.deleteSpecificComputer(Integer.parseInt(id));
				}
			}
			
			   return "redirect:/dashboard";
		   }
		   */
		   /*
		   @RequestMapping(method = RequestMethod.POST)
		   public String PostHandlerEdit(@RequestParam(required = false, value = "Edit")String edit,@RequestParam(required = false, value = "name") String name,
				   @RequestParam(required = false, value = "discontinued") String discontinued,@RequestParam(required = false, value = "introduced") String introduced,
				   @RequestParam(required = false, value = "company") String company,@RequestParam(required = false, value = "id") String id)
		   {
			   if (edit != null) {
				   Edit(name,id,introduced, discontinued,company);
			   }
			   return "redirect:/dashboard";
		  
		   }*/
		   
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
				list = serviceComputer.getAllComputerOrderBy("computer.name", start, end);
			else if ((Order != null) && ((Order.equals("company"))))
				list = serviceComputer.getAllComputerOrderBy("company.name", start, end);
			else
				list = serviceComputer.getAllComputer(Math.abs(start), Math.abs(end));
			return list;
		}
		protected  void Edit(String name,String id,String introduced, String discontinued,String c)
		{

			CompanyDTO company = CompanyDTO.Builder.newInstance().setId(c).build();
			ComputerDTO newComputer = ComputerDTO.Builder.newInstance().setName(name).setCompany(company)
					.setIntroduced(introduced).setDiscontinued(discontinued).setId(id).build();
			try {
				serviceComputer.updateComputer(newComputer);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		protected  List<ComputerDTO> SetOrder(HttpServletRequest request, int start, int end) {
			List<ComputerDTO> list = new ArrayList<>();
			ServiceComputer serviceComputer =  SpringConfiguration.getContext().getBean(ServiceComputer.class);
			if ((request.getParameter("Order") != null) && ((request.getParameter("Order").equals("computer"))))
				list = serviceComputer.getAllComputerOrderBy("computer.name", start, end);
			else if ((request.getParameter("Order") != null) && ((request.getParameter("Order").equals("company"))))
				list = serviceComputer.getAllComputerOrderBy("company.name", start, end);
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
