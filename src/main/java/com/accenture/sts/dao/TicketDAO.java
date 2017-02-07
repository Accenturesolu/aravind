package com.accenture.sts.dao;

import java.util.List;
import com.accenture.sts.entity.Application;
import com.accenture.sts.entity.Dev_comment;
import com.accenture.sts.entity.Documentation;
import com.accenture.sts.entity.Employee;
import com.accenture.sts.entity.Tester_comment;
import com.accenture.sts.entity.Ticket;
import com.accenture.sts.entity.Upload_ticket;

public interface TicketDAO {

	public List<Application> showAllApp();

	public boolean addticket(String empID, String ticketid, String ticket_desc, Employee employee, Application appl);

	public List<Object[]> showDeleteTickets();

	public void deleteTicket(String ticket_id);

	public List<Object[]> showclosedtickets(String empID);

	public int updateDocument(String Ticketid, Documentation document, Ticket ticket1);

	public List<Object[]> ticketList(String emp_Id);

	public List<Object[]> showClosedTickets(String emp_Id);

	public int assignTicket(Employee emp, Ticket ticket, String ticket_id, String application_name);

	public List<Object[]> showToassign();

	public List<Employee> showEmployee();

	public List<Object[]> showToreassign();

	public int reassignTicket(String oldemp, String newemp, Ticket ticket);

	public int updateTicket(Ticket ticket, Dev_comment dev, Tester_comment test, String app);

	public Application getApplicationDetails(String application_name);

	public Employee getEmployeeDetails(String empid);

	public int releaseTicket(String ticketId, String release);

	public List<Object[]> showRelease();
	
	public List<Upload_ticket> showDate();
}
