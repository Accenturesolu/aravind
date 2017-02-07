package com.accenture.sts.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.accenture.sts.entity.Application;
import com.accenture.sts.entity.Dev_comment;
import com.accenture.sts.entity.Documentation;
import com.accenture.sts.entity.Employee;
import com.accenture.sts.entity.Tester_comment;
import com.accenture.sts.entity.Ticket;
import com.accenture.sts.entity.Upload_ticket;
import com.accenture.sts.message.Messages;
import com.accenture.sts.service.StatusService;
import com.accenture.sts.service.TicketService;

/**
 * 
 * @author arthi.ravichandran, a.vignesh.nallasivam, ananthababu.ganesan, aravind.c.kumar, n.e.subramanian, l.madabhushi
 * @since - 25-01-2017
 * @version - 1.0
 */
@Controller
public class TicketController {
	private static Logger log = Logger.getLogger(TicketController.class);
	@Autowired
	private TicketService ticketService;
	@Autowired
	private StatusService statusService;
	
	Messages message = new Messages();

	/**
	 * This method is to display all the application name in add ticket page
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */
	@RequestMapping("/add.htm")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		String empID = (String) session.getAttribute("emp_ID");

		List<Application> appList = new ArrayList<Application>();
		appList = ticketService.getAllApp();

		Employee employee = new Employee();
		employee = ticketService.getEmployeeDetails(empID);
		String admin = employee.getIs_admin();
		mv.addObject("appList", appList);

		if (("Yes").equalsIgnoreCase(admin)) {
			mv.setViewName("/addticket");
		} else {
			mv.setViewName("/addticketuser");
		}
		return mv;
	}

	/**
	 * This method is to add the tickets manually
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws IOException 
	 */	 
	@RequestMapping("/addticket.htm")
	public ModelAndView addticket(HttpServletRequest request, HttpServletResponse response) throws IOException{

		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		String empID = (String) session.getAttribute("emp_ID");
		String ticketid = request.getParameter("tickid");
		String ticket_desc = request.getParameter("desc");
		String application_name = request.getParameter("app");

		log.info("creating new ticket with ticketId :" + ticketid);
		Ticket ticket1 = new Ticket();
		Application appl = new Application();
		Employee employee = new Employee();
		ticket1.setTicket_id(ticketid);
		ticket1.setTicket_desc(ticket_desc);
		appl.setApplication_name(application_name);
		employee.setEmployee_id(empID);

		boolean insertStatus = ticketService.addticket(empID, ticketid, ticket_desc, employee, appl);
		List<Application> appList = new ArrayList<Application>();
		appList = ticketService.getAllApp();

		employee = ticketService.getEmployeeDetails(empID);
		String admin = employee.getIs_admin();

		mv.addObject("evalue", empID);
		mv.addObject("appList", appList);
		request.setAttribute("tickid", ticketid);
		request.setAttribute("desc", ticket_desc);
		request.setAttribute("app", application_name);
		
		if (("Yes").equalsIgnoreCase(admin)) {
			if (insertStatus == true) {
				mv.addObject("SUCCESS", message.getSuccessadd());
				log.info("Successfully created ticket " + ticketid);
				mv.setViewName("/addticket");
			} else {
				mv.addObject("MESSAGE", message.getFailadd());
				log.info("TicketID " + ticketid + " already exists");
				mv.setViewName("/addticket");
			}
		} else {
			if (insertStatus == true) {
				mv.addObject("SUCCESS", message.getSuccessadd());
				log.info("Successfully created ticket " + ticketid);
				mv.setViewName("/addticketuser");
			} else {
				mv.addObject("MESSAGE", message.getFailadd());
				log.info("TicketID " + ticketid + " already exists");
				mv.setViewName("/addticketuser");
			}
		}
		return mv;
	}
	
	/**
	 * This method is to display all the tickets in the delete page
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */	 
	@RequestMapping("/showdelete.htm")
	public ModelAndView showdelete(HttpServletRequest request, HttpServletResponse response) {
	
		ModelAndView mv = new ModelAndView();
		List<Object[]> showdeleteticket = ticketService.showDeleteTickets();

		mv.addObject("ticket", showdeleteticket);
		mv.setViewName("/delete");
		return mv;

	}

	/**
	 * This method is to delete the tickets in the delete page
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws IOException 
	 */	 
	@RequestMapping("/deleteticket.htm")
	public ModelAndView deleteTicket(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
		ModelAndView mv = new ModelAndView();
		String ticket_id = request.getParameter("ticketId");

		ticketService.deleteTicket(ticket_id);
		List<Object[]> showdeleteticket = ticketService.showDeleteTickets();

		mv.addObject("SUCCESS", message.getSuccessdelete());
		mv.addObject("ticket", showdeleteticket);
		mv.setViewName("/delete");
		return mv;

	}

	/**
	 * This method is to display all closed tickets
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */	 
	@RequestMapping("/closedtickets.htm")
	public ModelAndView closedticket(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		String empID = (String) session.getAttribute("emp_ID");
		List<Object[]> closedtickets = new ArrayList<Object[]>();
		closedtickets = ticketService.getclosedTickets(empID);

		Employee employee = new Employee();
		employee = ticketService.getEmployeeDetails(empID);
		String admin = employee.getIs_admin();
		mv.addObject("closedtickets", closedtickets);

		if (("Yes").equalsIgnoreCase(admin)) {
			mv.setViewName("/myclosedtickets");
		} else {
			mv.setViewName("/myclosedticketsuser");
		}
		return mv;
	}

	/**
	 * This method is to update the document details
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws IOException 
	 */	 
	@RequestMapping("/updatedocument.htm")
	public ModelAndView updatedocumentadmin(HttpServletRequest request, HttpServletResponse response) throws IOException  {

		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		String empID = (String) session.getAttribute("emp_ID");
		String pass = (String) session.getAttribute("password");
		String name = (String) session.getAttribute("name");

		Documentation document = new Documentation();
		Ticket ticket1 = new Ticket();

		String Ticketid = request.getParameter("tickid");
		String Remedy = request.getParameter("rem");
		String Document = request.getParameter("doc");
		String Comment = request.getParameter("comment");

		ticket1.setTicket_id(Ticketid);
		document.setRemedy(Remedy);
		document.setDocument_desc(Document);
		document.setDoc_comment(Comment);

		int status = ticketService.updateDocumentDetails(Ticketid, document, ticket1);

		List<Object[]> TicketsList = new ArrayList<Object[]>();
		List<Object[]> DocumentList = new ArrayList<Object[]>();
		List<Object[]> clarifyList = new ArrayList<Object[]>();
		TicketsList = ticketService.ticketList(empID);
		DocumentList = ticketService.fetchTicket(empID);
		clarifyList = statusService.assignedClarification(empID);
		
		mv.addObject("allTickets", TicketsList);
		mv.addObject("allTickets1", DocumentList);
		request.setAttribute("name", name);
		request.setAttribute("emp_ID", empID);
		request.setAttribute("password", pass);
		request.setAttribute("remedy", Remedy);
		request.setAttribute("document", Document);
		request.setAttribute("comment", Comment);

		Employee employee = new Employee();
		employee = ticketService.getEmployeeDetails(empID);
		String admin = employee.getIs_admin();
		if (("Yes").equalsIgnoreCase(admin)) {
			if (status == 1) {
				mv.setViewName("/homeadmin");

			} else {
				mv.addObject("MESSAGE", message.getErrorupdate());
				mv.setViewName("/updatedocadmin");
			}
		} else {
			if (status == 1) {
				mv.addObject("clarifyList", clarifyList);
				mv.setViewName("/homeuser");

			} else {
				mv.addObject("MESSAGE", message.getErrorupdate());
				mv.setViewName("/updatedocuser");
			}
		}
		return mv;
	}

	/**
	 * This method is to showing the ticket to assign page
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */	 
	@RequestMapping("showtoassign.htm")
	public ModelAndView showToAssign(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView();
		List<Object[]> showToassign = ticketService.showToassign();
		List<Employee> emplist = ticketService.showEmployee();

		mv.addObject("showToassign", showToassign);
		mv.addObject("emplist", emplist);
		mv.setViewName("assignticket");
		return mv;
	}

	/**
	 * This method is to assign the ticket to resource
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */	 
	@ResponseBody
	@RequestMapping("assignticket.htm")
	public String assignTicket(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("ticket_id") String ticket_id, @RequestParam("ticket_desc") String ticket_desc,
			@RequestParam("app_name") String app_name, @RequestParam("empname") String empname) throws ClassNotFoundException, SQLException {
		
		String ticketid = request.getParameter("ticket_id");
		String ticketdesc = request.getParameter("ticket_desc");
		String application_name = request.getParameter("app_name");
		String employee_name = request.getParameter("empname");

		Ticket ticket = new Ticket();
		Employee emp = new Employee();

		ticket.setTicket_id(ticketid);
		ticket.setTicket_desc(ticketdesc);
		emp.setEmployee_name(employee_name);

		int insertStatus = ticketService.assignnewTicket(emp, ticket, ticket_id, application_name);
		
		if (insertStatus > 0) {
			return "Success";
		} else {
			return "Failed";
		}
	}

	/**
	 * This method is to show the tickets to reassign page
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */	 
	@RequestMapping("showtoreassign.htm")
	public ModelAndView showToReAssign(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView();
		List<Object[]> showToreassign = ticketService.showToreassign();
		List<Employee> emplist = ticketService.showEmployee();

		mv.addObject("showToreassign", showToreassign);
		mv.addObject("emplist", emplist);
		mv.setViewName("reassignticket");
		return mv;

	}

	/**
	 * This method is to reassign the ticket to resource
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */	 
	@ResponseBody
	@RequestMapping("reassignticket.htm")
	public String reassignTicket(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("ticket_id") String ticket_id, @RequestParam("ticket_desc") String ticket_desc,
			@RequestParam("oldempname") String oldempname, @RequestParam("newempname") String newempname) {
		
		
		String ticketid = request.getParameter("ticket_id");
		String ticketdesc = request.getParameter("ticket_desc");
		String oldemp = request.getParameter("oldempname");
		String newemp = request.getParameter("newempname");

		Ticket ticket = new Ticket();
		ticket.setTicket_id(ticketid);
		ticket.setTicket_desc(ticketdesc);

		int insertStatus = ticketService.reassignTicket(oldemp, newemp, ticket);
		
		if (insertStatus > 0) {
			return "Success";
		} else {
			return "Failed";
		}
	}

	/**
	 * This method is to update the ticket details
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws IOException 
	 */	 
	@RequestMapping("/updateTicket.htm")
	public ModelAndView updateticketadmin(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		String empID = (String) session.getAttribute("emp_ID");
		
		Ticket ticket = new Ticket();
		Dev_comment dev = new Dev_comment();
		Tester_comment test = new Tester_comment();
		String ticketid = request.getParameter("tickid");
		String ticket_type = request.getParameter("ticktype");
		String description = request.getParameter("desc");
		String app = request.getParameter("app");
		String priority = request.getParameter("priority");
		String worktoday = request.getParameter("worktoday");
		String activity = request.getParameter("activity");
		String status = request.getParameter("status");
		String tester = request.getParameter("tester");
		String comments = request.getParameter("comments");

		Employee employee = ticketService.getEmployeeDetails(empID);
		Application application = ticketService.getApplicationDetails(app);

		ticket.setTicket_id(ticketid);
		ticket.setTicket_type(ticket_type);
		ticket.setTicket_desc(description);
		ticket.setApplication(application);
		ticket.setPriority(Integer.parseInt(priority));
		ticket.setWorked_on_today(worktoday);
		dev.setTicket(ticket);
		dev.setActivity(activity);
		dev.setStatus(status);
		dev.setComment_desc(comments);
		test.setTicket(ticket);
		test.setTester(tester);
		int result = ticketService.updateTicketDetails(ticket, dev, test, app);

		List<Object[]> TicketsList = new ArrayList<Object[]>();
		List<Object[]> DocumentList = new ArrayList<Object[]>();
		List<Object[]> clarifyList = new ArrayList<Object[]>();
		TicketsList = ticketService.ticketList(empID);
		DocumentList = ticketService.fetchTicket(empID);
		clarifyList = statusService.assignedClarification(empID);
		mv.addObject("allTickets", TicketsList);
		mv.addObject("allTickets1", DocumentList);		
		
		employee = ticketService.getEmployeeDetails(empID);
		String admin = employee.getIs_admin();
		if (("Yes").equalsIgnoreCase(admin)) {
			if (result > 0) {
				mv.setViewName("homeadmin");
			} else {
				mv.addObject("FAILED", message.getErrorupdate());
				mv.setViewName("updateTicket");
			}
		} else {
			if (result > 0) {
				mv.addObject("clarifyList", clarifyList);
				mv.setViewName("homeuser");
			} else {
				mv.addObject("FAILED", message.getErrorupdate());
				mv.setViewName("updateticketuser");
			}
		}
		return mv;
	}

	/**
	 * This method is to display the ticket details
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */	 
	@RequestMapping("/release.htm")
	public ModelAndView release(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView();
		List<Object[]> TicketsList = new ArrayList<Object[]>();
		TicketsList = ticketService.release();
		mv.addObject("releaseTickets", TicketsList);

		mv.setViewName("/releaseticket");
		return mv;

	}

	/**
	 * This method is to update the release ticket status
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 */	 
	
	@RequestMapping("/releaseticket.htm")
	public ModelAndView addResource(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		String ticketId = request.getParameter("ticketid");
		String description = request.getParameter("desc");
		String application = request.getParameter("app");
		String name = request.getParameter("resname");
		String release = request.getParameter("release");
		System.out.println("tick" + ticketId);
		System.out.println("release" + release);
		Application app = new Application();
		Ticket ticket = new Ticket();
		Employee employee = new Employee();

		ticket.setTicket_id(ticketId);
		app.setApplication_name(application);
		ticket.setTicket_desc(description);
		ticket.setRelease_ticket(release);
		employee.setEmployee_name(name);
		int status = 0;
		status = ticketService.releaseTicket(ticketId, release);
		List<Object[]> TicketsList = new ArrayList<Object[]>();

		TicketsList = ticketService.release();

		if (status > 0) {
			mv.addObject("releaseTickets", TicketsList);
			mv.addObject("success", "Updated Successfully");
			mv.setViewName("/releaseticket");
		} else {
			mv.addObject("releaseTickets", TicketsList);
			mv.addObject("err", "Error in updation...Please check the filed and retry");
			mv.setViewName("/releaseticket");
		}
		mv.setViewName("/releaseticket");
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	public List<Upload_ticket> showDate() {

		@SuppressWarnings("deprecation")
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Upload_ticket.class, "upload_ticket");

		ProjectionList projList = Projections.projectionList();

		projList.add(Projections.max("upload_ticket.createdOn"));

		criteria.setProjection(projList);
		

		List<Upload_ticket> List = criteria.list();

		session.getTransaction().commit();
		session.close();
		return List;
	}
}



