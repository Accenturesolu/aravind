package com.accenture.sts.service;

import java.sql.SQLException;
import java.util.List;
import com.accenture.sts.entity.Application;
import com.accenture.sts.entity.Dev_comment;
import com.accenture.sts.entity.Documentation;
import com.accenture.sts.entity.Employee;
import com.accenture.sts.entity.Tester_comment;
import com.accenture.sts.entity.Ticket;
import com.accenture.sts.entity.Upload_ticket;

public interface TicketService {

	public List<Application> getAllApp();

	public boolean addticket(String empID, String ticketid, String ticket_desc, Employee employee, Application appl);

	public List<Object[]> showDeleteTickets();

	public void deleteTicket(String ticket_id);

	public List<Object[]> getclosedTickets(String empID);

	public int updateDocumentDetails(String Ticketid, Documentation document, Ticket ticket1);

	public List<Object[]> ticketList(String emp_Id);

	public List<Object[]> fetchTicket(String emp_Id);

	public List<Object[]> showToassign() ;

	public List<Employee> showEmployee() ;

	int assignnewTicket(Employee emp, Ticket ticket, String ticket_id, String application_name)
			throws ClassNotFoundException, SQLException;

	public int reassignTicket(String oldemp, String newemp, Ticket ticket) ;

	public List<Object[]> showToreassign() ;

	int updateTicketDetails(Ticket ticket, Dev_comment dev, Tester_comment test, String app);

	public Employee getEmployeeDetails(String empid);

	public Application getApplicationDetails(String app);

	public int releaseTicket(String ticketId, String release);

	public List<Object[]> release();
	public List<Upload_ticket> showDate();

}
