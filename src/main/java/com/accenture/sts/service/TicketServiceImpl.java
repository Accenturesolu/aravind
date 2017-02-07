package com.accenture.sts.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.accenture.sts.dao.TicketDAO;
import com.accenture.sts.entity.Application;
import com.accenture.sts.entity.Dev_comment;
import com.accenture.sts.entity.Documentation;
import com.accenture.sts.entity.Employee;
import com.accenture.sts.entity.Tester_comment;
import com.accenture.sts.entity.Ticket;
import com.accenture.sts.entity.Upload_ticket;

public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDAO ticketDAO;

	@Override
	public List<Application> getAllApp() {

		List<Application> appList = new ArrayList<Application>();
		try {
			appList = ticketDAO.showAllApp();
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return appList;
	}

	@Override
	public boolean addticket(String empID, String ticketid, String ticket_desc, Employee employee, Application appl)  {

		boolean insertStatus = false;
		try {
			insertStatus = ticketDAO.addticket(empID, ticketid, ticket_desc, employee, appl);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return insertStatus;
	}

	@Override
	public List<Object[]> showDeleteTickets() {

		List<Object[]> ticket = new ArrayList<>();
		try {
			ticket = ticketDAO.showDeleteTickets();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		return ticket;
	}

	@Override
	public void deleteTicket(String ticket_id) {

		try {
			ticketDAO.deleteTicket(ticket_id);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Override
	public List<Object[]> getclosedTickets(String empID) {

		List<Object[]> closedtickets = new ArrayList<Object[]>();
		try {
			closedtickets = ticketDAO.showclosedtickets(empID);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return closedtickets;

	}

	@Override
	public int updateDocumentDetails(String Ticketid, Documentation document, Ticket ticket1) {

		int status = 0;
		try {
			status = ticketDAO.updateDocument(Ticketid, document, ticket1);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return status;
	}

	@Override
	public List<Object[]> ticketList(String emp_Id) {

		List<Object[]> ticket = new ArrayList<Object[]>();
		try {
			ticket = ticketDAO.ticketList(emp_Id);
		} catch (Exception exception) {
			System.out.println("Exception is :" + exception.getMessage());
		}
		return ticket;
	}

	@Override
	public List<Object[]> fetchTicket(String emp_Id) {

		List<Object[]> showDocument = new ArrayList<Object[]>();
		try {
			showDocument = ticketDAO.showClosedTickets(emp_Id);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return showDocument;
	}

	@Override
	public int assignnewTicket(Employee emp, Ticket ticket, String ticket_id, String application_name)
			 {

		int update = 0;
		try {
			update = ticketDAO.assignTicket(emp, ticket, ticket_id, application_name);
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Exception e");
		}
		return update;
	}

	@Override
	public List<Object[]> showToassign()  {

		List<Object[]> insert = null;
		try {
			insert = ticketDAO.showToassign();
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return insert;
	}

	@Override
	public List<Employee> showEmployee()  {

		List<Employee> emplist = null;
		try {
			emplist = ticketDAO.showEmployee();
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return emplist;
	}

	public List<Object[]> showToreassign() {

		List<Object[]> insert = null;
		try {
			insert = ticketDAO.showToreassign();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Exception e");
		}
		return insert;
	}

	public int reassignTicket(String oldemp, String newemp, Ticket ticket) {

		int update = 0;
		try {
			update = ticketDAO.reassignTicket(oldemp, newemp, ticket);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return update;
	}

	@Override
	public int updateTicketDetails(Ticket ticket, Dev_comment dev, Tester_comment test, String app) {

		int status = 0;
		try {
			status = ticketDAO.updateTicket(ticket, dev, test, app);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return status;

	}

	public Employee getEmployeeDetails(String empid) {

		Employee emp_list = null;
		try {
			emp_list = ticketDAO.getEmployeeDetails(empid);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return emp_list;

	}

	public Application getApplicationDetails(String app) {

		Application app_list = null;
		try {
			app_list = ticketDAO.getApplicationDetails(app);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return app_list;
	}

	public int releaseTicket(String ticketId, String release) {

		int status = 0;
		try {
			status = ticketDAO.releaseTicket(ticketId, release);
		} catch (Exception exception) {
			System.out.println("Exception e:" + exception.getMessage());
		}
		return status;
	}

	public List<Object[]> release() {

		List<Object[]> showTickets = new ArrayList<Object[]>();
		try {
			showTickets = ticketDAO.showRelease();
		} catch (Exception exception) {
			System.out.println("Exception e:" + exception.getMessage());
		}
		return showTickets;
	}
	

	public List<Upload_ticket> showDate() {

		List<Upload_ticket> insert = null;
		try {
			insert = ticketDAO.showDate();
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return insert;
	
	}
}
