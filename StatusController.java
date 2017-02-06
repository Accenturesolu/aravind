package com.accenture.sts.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.accenture.sts.entity.Employee;
import com.accenture.sts.entity.Ticket;
import com.accenture.sts.message.Messages;
import com.accenture.sts.service.StatusService;
import com.accenture.sts.service.TicketService;

/**
 * 
 * @author arthi.ravichandran, ananthababu.ganesan, n.e.subramanian
 * @since - 25-01-2017
 * @version - 1.0
 */
@Controller
public class StatusController {
	private static Logger log = Logger.getLogger(StatusController.class);

	@Autowired
	private StatusService statusService;
	@Autowired
	private TicketService ticketService;

	Messages message = new Messages();

	/**
	 * This method is to display the status of today's ticket
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */
	@RequestMapping("/dayticket.htm")
	public ModelAndView dayticket(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		Employee employee = new Employee();
		Ticket ticket = new Ticket();
		List<Object[]> TicketList = new ArrayList<Object[]>();
		TicketList = statusService.getAllReport(employee, ticket);
		mv.addObject("dayticket", TicketList);
		mv.setViewName("/ticketoftheday");
		return mv;
	}

	/**
	 * This method is to display all the resource name in ticketstatus page
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */
	
	@RequestMapping("/resource.htm")
	public ModelAndView resource(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		List<Employee> resList = new ArrayList<Employee>();
		resList = statusService.getAllResource();
		mv.addObject("resList", resList);
		mv.setViewName("/ticketstatus");
		return mv;
	}

	/**
	 * This method is to display all the ticket details
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */
	@RequestMapping("/ticketstatus.htm")
	public ModelAndView ticketstatus(HttpServletRequest request, HttpServletResponse response) {

		String ticketid = request.getParameter("ticketid");
		String resource = request.getParameter("resource");

		if (ticketid == "") {
			ticketid = "select";
		}

		ModelAndView mv = new ModelAndView();
		List<Employee> resList = new ArrayList<Employee>();
		resList = statusService.getAllResource();

		List<Object[]> ticketList = new ArrayList<Object[]>();
		ticketList = statusService.getTicketstatus(ticketid, resource);
		mv.addObject("allTickets", ticketList);
		mv.addObject("resList", resList);
		mv.setViewName("/ticketstatus");
		request.setAttribute("resource", resource);
		request.setAttribute("ticketid", ticketid);
		return mv;
	}

	/**
	 * This method is to display all the resource name in daystatus page
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws IOException 
	 */
	@RequestMapping("/dayresource.htm")
	public ModelAndView dayresource(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ModelAndView mv = new ModelAndView();
		List<Employee> resList = new ArrayList<Employee>();
		resList = statusService.getAllResource();
		mv.addObject("MESSAGE", message.getMandatory());
		mv.addObject("resList", resList);
		mv.setViewName("/daystatus");
		return mv;
	}

	/**
	 * This method is to display all the ticket details based on days
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */
	@RequestMapping("/daystatus.htm")
	public ModelAndView daystatus(HttpServletRequest request, HttpServletResponse response) {

		String ticketid = request.getParameter("ticketid");
		String resource = request.getParameter("resource");
		String days = request.getParameter("days");

		if (ticketid == "") {
			ticketid = "select";
		}

		ModelAndView mv = new ModelAndView();
		List<Employee> resList = new ArrayList<Employee>();
		resList = statusService.getAllResource();

		List<Object[]> dayList = new ArrayList<Object[]>();
		dayList = statusService.getDaystatus(ticketid, resource, days);

		mv.addObject("resList", resList);
		mv.addObject("daystatus", dayList);
		mv.addObject("resource", resource);
		mv.addObject("days", days);
		mv.addObject("ticketid", ticketid);
		mv.setViewName("/daystatus");
		request.setAttribute("resource", resource);
		request.setAttribute("days", days);
		request.setAttribute("ticketid", ticketid);
		return mv;
	}

	/**
	 * This method is to display the resource name in report summary page
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */
	@RequestMapping("/reportsummary.htm")
	public ModelAndView resname(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		List<Object[]> NameList = new ArrayList<Object[]>();
		NameList = statusService.namelist();
		List<Object[]> AppList = new ArrayList<Object[]>();
		AppList = statusService.applist();

		mv.addObject("applist", AppList);
		mv.addObject("namelist", NameList);
		mv.setViewName("/reportsummary");
		return mv;
	}

	/**
	 * This method is to display the report summary page
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */
	@RequestMapping("/report.htm")
	public ModelAndView getReport(HttpServletRequest request, HttpServletResponse response) {

		Ticket ticket = new Ticket();
		String resname = request.getParameter("empName");
		String appname = request.getParameter("application");
		String activity = request.getParameter("stateId");
		String curname = request.getParameter("status");
		String release = request.getParameter("release");
		String startDateFrom = request.getParameter("startDateFrom");
		String startDateTo = request.getParameter("startDateTo");
		String endDateFrom = request.getParameter("endDateFrom");
		String endDateTo = request.getParameter("endDateTo");
		
		List<Object[]> resList = new ArrayList<Object[]>();

		resList = statusService.getAllResource(resname, appname, activity, curname, release, startDateFrom,
				startDateTo,endDateFrom,endDateTo);

		List<Object[]> NameList = new ArrayList<Object[]>();
		NameList = statusService.namelist();
		List<Object[]> AppList = new ArrayList<Object[]>();
		AppList = statusService.applist();

		ModelAndView mv = new ModelAndView();
		mv.addObject("applist", AppList);
		mv.addObject("namelist", NameList);
		mv.addObject("ticket", ticket);
		mv.addObject("allTickets", resList);
		mv.setViewName("/reportsummary");
		request.setAttribute("empName", resname);
		request.setAttribute("application", appname);
		request.setAttribute("stateId", activity);
		request.setAttribute("status", curname);
		request.setAttribute("startDateFrom", startDateFrom);
		request.setAttribute("startDateTo", startDateTo);
		request.setAttribute("endDateFrom", endDateFrom);
		request.setAttribute("endDateTo", endDateTo);
		request.setAttribute("release", release);
		return mv;
	}


	/**
	 * This method is to display the daily status report
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@RequestMapping("dailystatus.htm")
	public ModelAndView dailystatus(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

		HttpSession session = request.getSession();

		String empID = (String) session.getAttribute("emp_ID");
		ModelAndView mv = new ModelAndView();
		List<Object[]> dailystatus = statusService.getDailystatus();

		Employee employee = new Employee();
		employee = ticketService.getEmployeeDetails(empID);
		String admin = employee.getIs_admin();

		mv.addObject("dailystatus", dailystatus);
		mv.addObject("admin", admin);
		mv.setViewName("dailystatus");
		return mv;
	}

	/**
	 *This method is to display the not reported employee details
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@RequestMapping("notreportedemployee.htm")
	public ModelAndView notreportedemployee(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

		ModelAndView mv = new ModelAndView();
		List<Object[]> notReported = statusService.getNotreportedList();

		mv.addObject("notReported", notReported);
		mv.setViewName("notreportedemployee");
		return mv;
	}

	/**
	 * This method is to display the Inprogress ticket details
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@RequestMapping("nochangeinstatus.htm")
	public ModelAndView nochangeinstatus(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

		ModelAndView mv = new ModelAndView();
		List<Object[]> inProgress = statusService.getInprogressTicket();

		mv.addObject("inProgress", inProgress);
		mv.setViewName("inprogressticket");
		return mv;
	}

	/**
	 * This method is to fetch all the ticket details from clarification
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */
	@RequestMapping("/clarificationreport.htm")
	public ModelAndView calrificationreport(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		String emp_ID = (String) session.getAttribute("emp_ID");
		String name = (String) session.getAttribute("name");
		String pass = (String) session.getAttribute("pass1");

		List<Object[]> ticketLists = statusService.allclarify();
		ModelAndView mv = new ModelAndView();

		mv.addObject("ticketList", ticketLists);
		mv.setViewName("/clarification");
		request.setAttribute("name", name);
		request.setAttribute("pass1", pass);
		request.setAttribute("EmpID", emp_ID);
		return mv;
	}

	/**
	 * This method is to select a particular ticket which needed clarification
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws IOException 
	 */
	@RequestMapping("/toclarification.htm")
	public ModelAndView toclarification(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();

		String emp_ID = (String) session.getAttribute("emp_ID");
		String name = (String) session.getAttribute("name");
		String pass = (String) session.getAttribute("pass1");
		String ticketID = request.getParameter("ticketId");

		List<Object[]> ticketList = statusService.toClarify(ticketID);
		List<Object[]> ticketLists = statusService.allclarify();

		ModelAndView mv = new ModelAndView();

		if (ticketList.isEmpty()) {
			mv.addObject("MESSAGE", message.getVerifyticketid());
			mv.addObject("ticketList", ticketLists);
			request.setAttribute("name", name);
			request.setAttribute("pass1", pass);
			request.setAttribute("EmpID", emp_ID);
			mv.setViewName("/clarification");
		} else {
			mv.addObject("allTickets", ticketList);
			mv.addObject("ticketList", ticketLists);
			request.setAttribute("name", name);
			request.setAttribute("pass1", pass);
			request.setAttribute("EmpID", emp_ID);
			mv.setViewName("/clarification");
		}
		for (int i = 0; i < ticketList.size(); i++) {
			System.out.println("ticketList in controller:" + ticketList.get(i));
		}
		return mv;
	}

	/**
	 * This method is to update the supervisor comments
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws IOException 
	 */
	@RequestMapping("/clarification.htm")
	public ModelAndView clarification(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("ticketId") String ticketID,
			@RequestParam(value = "comment", required = false) String supervisorcomment) throws IOException {

		HttpSession session = request.getSession();

		String emp_ID = (String) session.getAttribute("emp_ID");
		String name = (String) session.getAttribute("name");
		String pass = (String) session.getAttribute("pass1");

		int status = statusService.calrify(ticketID, supervisorcomment);

		List<Object[]> ticketLists = statusService.allclarify();

		ModelAndView mv = new ModelAndView();
		if (status > 0) {
			mv.addObject("MESSAGE", message.getClarificationupdate());
		}

		mv.addObject("ticketList", ticketLists);
		System.out.println(ticketLists);
		log.info("Successfully updated");
		request.setAttribute("name", name);
		request.setAttribute("pass1", pass);
		request.setAttribute("EmpID", emp_ID);
		mv.setViewName("/clarification");
		return mv;
	}

	/**
	 * This method is to update the employee comments
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("/responseclarification.htm")
	public ModelAndView responseclarification(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("ticketId") String TicketID, @RequestParam("response") String supervisorComment,
			@RequestParam("release") String employee_response) throws IOException {

		HttpSession session = request.getSession();

		String pass = (String) session.getAttribute("password");
		String empID = (String) session.getAttribute("emp_ID");

		int status = statusService.responseclarification(TicketID, employee_response, supervisorComment);
		List<Object[]> TicketsList = new ArrayList<Object[]>();
		List<Object[]> DocumentList = new ArrayList<Object[]>();
		List<Object[]> clarifyList = new ArrayList<Object[]>();

		ModelAndView mv = new ModelAndView();
		if (status > 0) {
			mv.addObject("status", message.getClarificationresponse());
		}

		TicketsList = ticketService.ticketList(empID);
		DocumentList = ticketService.fetchTicket(empID);
		clarifyList = statusService.assignedClarification(empID);

		mv.addObject("allTickets", TicketsList);
		mv.addObject("allTickets1", DocumentList);
		mv.addObject("clarifyList", clarifyList);
		request.setAttribute("pass1", pass);
		request.setAttribute("EmpID", empID);
		mv.setViewName("/homeuser");
		return mv;
	}

	/**
	 * This method is to delete the ticket details from clarification page
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws IOException 
	 */
	@RequestMapping("/deleteclarification.htm")
	public ModelAndView deleteclarification(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();

		String emp_ID = (String) session.getAttribute("emp_ID");
		String name = (String) session.getAttribute("name");
		String pass = (String) session.getAttribute("pass1");
		String TicketID = request.getParameter("deleteTicketId");
		String supervisor_comment = request.getParameter("comments");

		int status = statusService.deleteclarification(TicketID, supervisor_comment);
		List<Object[]> ticketLists = statusService.allclarify();
		ModelAndView mv = new ModelAndView();

		if (status > 0) {
			mv.addObject("status", message.getClarificationdelete());
		}
		mv.addObject("ticketList", ticketLists);
		request.setAttribute("name", name);
		request.setAttribute("EmpID", emp_ID);
		request.setAttribute("pass1", pass);
		mv.setViewName("/clarification");
		return mv;
	}

}
