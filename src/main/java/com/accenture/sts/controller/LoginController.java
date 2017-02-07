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
import com.accenture.sts.entity.Upload_ticket;
import com.accenture.sts.message.Messages;
import com.accenture.sts.service.LoginService;
import com.accenture.sts.service.StatusService;
import com.accenture.sts.service.TicketService;


/**
 * 
 * @author a.vignesh.nallasivam, n.e.subramanian
 * @since - 25-01-2017
 * @version - 1.0
 */
@Controller
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private StatusService statusService;

	Messages message = new Messages();

	/**
	 * This method is to check whether the user is valid on not
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws IOException 
	 */
	@RequestMapping("/searchResource.htm")
	public ModelAndView searchResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
HttpSession hs = request.getSession();
		
		String emp_Id = request.getParameter("emp_id");
		String pass = request.getParameter("password");
		hs.setAttribute("emp_ID", emp_Id);

		Employee employee = new Employee();
		
		employee = loginService.searchResource(emp_Id, pass);
		List<Upload_ticket> showDate = loginService.showDate();
		List<Upload_ticket> showCreate = loginService.showCreate();
		
		String employeename = employee.getEmployee_name();
		//Timestamp showdate= showDate.getCreatedon();
		HttpSession session = request.getSession();
		session.setAttribute("emp_ID", emp_Id);
		session.setAttribute("password", pass);
		session.setAttribute("name", employeename);
		session.setAttribute("showDate", showDate);
		session.setAttribute("showCreate", showCreate);
		System.out.println("show the date :"+showDate);

		ModelAndView mv = new ModelAndView();
		List<Object[]> TicketsList = new ArrayList<Object[]>();
		List<Object[]> DocumentList = new ArrayList<Object[]>();
		List<Object[]> clarifyList = new ArrayList<Object[]>();

		if (employee.getEmployee_id() == null || !employee.getEmployee_id().equals(emp_Id)) {
			mv.addObject("ERROR", message.getInvalidcredentials());
			mv.setViewName("/login");
			return mv;
		} else {
			boolean report = employee.isReport();
			if (("NO").equalsIgnoreCase(employee.getIs_active())) {
				mv.addObject("ERROR", message.getResourcenotactive());
				mv.setViewName("/login");

			} else {
				TicketsList = ticketService.ticketList(emp_Id);
				DocumentList = ticketService.fetchTicket(emp_Id);
				clarifyList = statusService.assignedClarification(emp_Id);
				mv.addObject("allTickets", TicketsList);
				mv.addObject("allTickets1", DocumentList);
				mv.addObject("emp_ID", emp_Id);
				mv.addObject("password", pass);
				mv.addObject("name", employeename);
				mv.addObject("showDate", showDate);
				mv.addObject("showCreate", showCreate);
				log.info("Successfully logged in");
				
				if (("Yes").equalsIgnoreCase(employee.getIs_admin())) {
					mv.setViewName("/homeadmin");
				} else {
					mv.addObject("clarifyList", clarifyList);
					mv.addObject("report", report);
					mv.setViewName("/homeuser");
				}
			}
		}
		return mv;
	}

	/**
	 * This method is logout - redirect the login page
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */
	@RequestMapping("/logout.htm")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String empID = null;
		String pass = null;
		String name = null;
		session.setAttribute("emp_ID", empID);
		session.setAttribute("password", pass);
		session.setAttribute("name", name);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/logout");
		return mv;
	}

	/**
	 * This method is to change the password
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws IOException 
	 */
	@RequestMapping("/changepassword.htm")
	public ModelAndView changepwdadmin(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		String empID = (String) session.getAttribute("emp_ID");
		String pass = (String) session.getAttribute("password");
		String password = request.getParameter("password");
		String new_pwd = request.getParameter("new_pwd");

		Employee employee = new Employee();
		int status = 0;
		employee.setPassword(new_pwd);
		if (pass.equals(password)) {
			status = loginService.changepwd(empID, employee);
		}

		employee = ticketService.getEmployeeDetails(empID);
		String admin = employee.getIs_admin();

		if (("Yes").equalsIgnoreCase(admin)) {
			if (status > 0) {
				mv.addObject("status", message.getPasswordchange());
				mv.setViewName("/login");
			} else {
				mv.addObject("err", message.getPasswordnotmatch());
				mv.setViewName("/changepasswordadmin");
			}
		} else {
			if (status > 0) {
				mv.addObject("status", message.getPasswordchange());
				mv.setViewName("/login");

			} else {
				mv.addObject("err", message.getPasswordnotmatch());
				mv.setViewName("/changepassword");
			}
		}
		return mv;
	}
}
