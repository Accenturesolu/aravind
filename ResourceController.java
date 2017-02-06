package com.accenture.sts.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.sts.entity.Employee;
import com.accenture.sts.exception.STSGenericException;
import com.accenture.sts.message.Messages;
import com.accenture.sts.service.ResourceService;


/**
 * 
 * @author n.e.subramanian
 * @since - 25-01-2017
 * @version - 1.0
 */
@Controller
public class ResourceController {
	private static Logger log = Logger.getLogger(ResourceController.class);

	@Autowired
	private ResourceService resourceService;

	Messages message = new Messages();

	/**
	 * This method is to add the resource details
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws IOException 
	 * @throws STSGenericException 
	 */
	@RequestMapping("/addResource.htm")
	public ModelAndView addResource(HttpServletRequest request, HttpServletResponse response) throws IOException, STSGenericException  {

		HttpSession session = request.getSession();
		
		if (request == null || response == null) {
			log.info("Request or Response failed for SEARCHVISITOR METHOD..");
			throw new STSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\STSLOG folder",
					new NullPointerException());
		}
		
		String empID = (String) session.getAttribute("emp_ID");
		String employeeid = request.getParameter("emp_id");
		String employeename = request.getParameter("emp_name");
		String isadmin = request.getParameter("admin_pref");
		String email = request.getParameter("email");

		Employee employee = new Employee();
		employee.setEmployee_name(employeename);
		employee.setEmployee_id(employeeid);
		employee.setIs_admin(isadmin);
		employee.setEmail(email);

		ModelAndView mv = new ModelAndView();
		int status = resourceService.createResource(empID, employee);

		if (status > 0) {
			mv.addObject("msg", message.getResourceinsert());

		} else {
			mv.addObject("err", message.getEmployeeidexist());
		}

		request.setAttribute("emp_id", employeeid);
		request.setAttribute("emp_name", employeename);
		request.setAttribute("admin_pref", isadmin);
		request.setAttribute("email", email);
		mv.setViewName("/addresource");
		return mv;

	}

	/**
	 * This method is to display the resource name in modifyresource page
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */
	@RequestMapping("/resname.htm")
	public ModelAndView resname(HttpServletRequest request, HttpServletResponse response) {
	
		ModelAndView mv = new ModelAndView();
		List<Object[]> NameList = new ArrayList<Object[]>();
		NameList = resourceService.resname();
		mv.addObject("namelist", NameList);
		mv.setViewName("/modifyresource");
		return mv;
	}

	/**
	 * This method is to modify the resource details
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws IOException 
	 */
	@RequestMapping("/modifyResource.htm")
	public ModelAndView modifyResource(HttpServletRequest request, HttpServletResponse response) throws IOException{
	
		ModelAndView mv = new ModelAndView();
		Employee employee = new Employee();

		String emp_name = request.getParameter("emp_name");
		String isactive = request.getParameter("isactive");
		String isadmin = request.getParameter("isadmin");
		String isinclude = request.getParameter("isinclude");
		boolean report = Boolean.valueOf(request.getParameter("report"));

		employee.setEmployee_name(emp_name);
		employee.setIs_active(isactive);
		employee.setIs_admin(isadmin);
		employee.setIs_include(isinclude);
		employee.setReport(report);

		List<Object[]> NameList = new ArrayList<Object[]>();
		NameList = resourceService.resname();
		int status = 1;
		resourceService.modifyResource(employee);
		if (status > 0) {
			mv.addObject("msg", message.getModifyresource());

		}

		mv.addObject("namelist", NameList);
		request.setAttribute("emp_name", emp_name);
		mv.setViewName("/modifyresource");
		return mv;
	}
}
