package com.accenture.sts.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.accenture.sts.dao.StatusDAO;
import com.accenture.sts.entity.Employee;
import com.accenture.sts.entity.Ticket;

public class StatusServiceImpl implements StatusService {
	@Autowired
	private StatusDAO statusDAO;

	public List<Object[]> getAllReport(Employee employee, Ticket ticket) {

		List<Object[]> Tickets = new ArrayList<Object[]>();
		try {
			Tickets = statusDAO.showAllReport(employee, ticket);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return Tickets;
	}

	public List<Employee> getAllResource() {

		List<Employee> resList = new ArrayList<Employee>();
		try {
			resList = statusDAO.showAllResource();
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return resList;
	}

	public List<Object[]> getTicketstatus(String ticketid, String resource) {

		List<Object[]> showtickets = new ArrayList<Object[]>();
		try {
			showtickets = statusDAO.showticketstatus(ticketid, resource);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return showtickets;
	}

	public List<Object[]> getDaystatus(String ticketid, String resource, String days) {

		List<Object[]> showdays = new ArrayList<Object[]>();
		try {
			showdays = statusDAO.showdaystatus(ticketid, resource, days);
		} catch (Exception exception) {
			System.out.println("Exception e");
		}
		return showdays;
	}

	public List<Object[]> namelist() {

		List<Object[]> resname = new ArrayList<Object[]>();
		try {
			resname = statusDAO.resname();
		} catch (Exception exception) {
			System.out.println("Exception e:" + exception.getMessage());
		}
		return resname;
	}

	public List<Object[]> applist() {

		List<Object[]> applist = new ArrayList<Object[]>();
		try {
			applist = statusDAO.applist();
		} catch (Exception exception) {
			System.out.println("Exception e:" + exception.getMessage());
		}
		return applist;
	}

	public List<Object[]> getAllResource(String resname, String appname, String activity, String curname,
			String release,  String startDateFrom, String startDateTo, String endDateFrom, String endDateTo) {

		List<Object[]> showResource = new ArrayList<Object[]>();
		try {
			System.out.println("surya");
			showResource = statusDAO.showAllResource(resname, appname, activity, curname, release,
					startDateFrom, startDateTo, endDateFrom, endDateTo);
		} catch (Exception exception) {
			System.out.println("Exception e:" + exception.getMessage());
		}
		return showResource;
	}

	public List<Object[]> getDailystatus() throws ClassNotFoundException, SQLException {

		List<Object[]> insert = null;
		try {
			insert = statusDAO.getDailystatus();
		} catch (Exception exception) {

		}
		return insert;
	}

	public List<Object[]> getInprogressTicket() throws ClassNotFoundException, SQLException {

		List<Object[]> insert = null;
		try {
			insert = statusDAO.getInprogressTicket();
		} catch (Exception exception) {

		}
		return insert;
	}

	public List<Object[]> getNotreportedList() throws ClassNotFoundException, SQLException {

		List<Object[]> insert = null;
		try {
			insert = statusDAO.getNotreportedList();
		} catch (Exception exception) {

		}
		return insert;
	}

	public List<Object[]> allclarify() {

		List<Object[]> showClarify = new ArrayList<Object[]>();
		try {
			showClarify = statusDAO.showclarify();
		} catch (Exception exception) {
			System.out.println("Exception e:" + exception.getMessage());
		}
		return showClarify;
	}

	public List<Object[]> toClarify(String ticketID) {

		List<Object[]> Tickets = new ArrayList<Object[]>();
		try {
			Tickets = statusDAO.toclarify(ticketID);
		} catch (Exception exception) {
			System.out.println("Exception e:" + exception.getMessage());
		}
		return Tickets;
	}

	public int calrify(String ticketID, String supervisorcomment) {

		int status = 0;
		try {
			status = statusDAO.clarify(ticketID, supervisorcomment);
		} catch (Exception exception) {
			System.out.println("Exception e:" + exception.getMessage());
		}
		return status;
	}

	public int responseclarification(String ticketID, String employee_response, String supervisorComment) {

		int status = 0;
		try {
			status = statusDAO.responseclarification(ticketID, employee_response, supervisorComment);
		} catch (Exception exception) {
			System.out.println("Exception e:" + exception.getMessage());
		}
		return status;
	}

	public List<Object[]> assignedClarification(String empID) {

		List<Object[]> ticketList = new ArrayList<Object[]>();
		try {
			ticketList = statusDAO.assignedClarification(empID);

		} catch (Exception exception) {
			System.out.println("Exception e:" + exception.getMessage());
		}
		return ticketList;
	}

	public int deleteclarification(String ticketID, String supervisor_comment) {

		int status = 0;
		try {
			status = statusDAO.deleteclarification(ticketID, supervisor_comment);

		} catch (Exception e) {
			System.out.println("Exception e:" + e.getMessage());
		}
		return status;
	}

}
